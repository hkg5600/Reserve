package com.example.reserve.ui.reservation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.example.reserve.R
import com.example.reserve.adapter.MarketListAdapter
import com.example.reserve.base.BaseActivity
import com.example.reserve.databinding.ActivityReservationBinding
import com.example.reserve.network.response.MarketList
import com.example.reserve.ui.order.OrderActivity
import kotlinx.android.synthetic.main.app_bar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReservationActivity : BaseActivity<ActivityReservationBinding, ReservationActivityViewModel>() {
    override val layoutResourceId = R.layout.activity_reservation
    override val viewModel: ReservationActivityViewModel by viewModel()
    private val marketAdapter : MarketListAdapter by inject()
    override fun initView() {
        title = "예약하기"
        setSupportActionBar(toolbar)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.back_button)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewDataBinding.recyclerViewMarket.run {
            setHasFixedSize(true)
            adapter = marketAdapter
        }
    }

    override fun initObserver() {
        viewModel.data.observe(this, Observer {
            when(it) {
                is MarketList -> {
                    Log.e("Market Data ", it.market.toString())
                    marketAdapter.marketList = it.market
                    marketAdapter.notifyDataSetChanged()
                }
            }
        })

        viewModel.error.observe(this, Observer {
            finish()
        })
    }

    override fun initListener() {
        marketAdapter.onItemClickListener = object : MarketListAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int, holder: MarketListAdapter.MarketListHolder) {
                startActivityForResult(Intent(this@ReservationActivity, OrderActivity::class.java).putExtra("marketId", marketAdapter.marketList[position].marketId), 1)
            }
        }

        viewDataBinding.editTextSearch.setOnEditorActionListener { textView, i, keyEvent ->
            return@setOnEditorActionListener  when (i) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    viewModel.getMarketList(viewDataBinding.editTextSearch.text.toString())
                    true
                }
                else -> false
            }
        }

        viewDataBinding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.getMarketList(viewDataBinding.editTextSearch.text.toString())
            }

        })
    }

    override fun initViewModel() {
        viewModel.getMarketList("")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
