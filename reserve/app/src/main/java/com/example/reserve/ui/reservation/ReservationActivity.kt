package com.example.reserve.ui.reservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.reserve.R
import com.example.reserve.adapter.MarketListAdapter
import com.example.reserve.base.BaseActivity
import com.example.reserve.databinding.ActivityReservationBinding
import com.example.reserve.network.response.MarketList
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
                    marketAdapter.setMarketList(it.market)
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
                //startActivityForResult(Intent(this, ))
            }

        }
    }

    override fun initViewModel() {
        viewModel.getMarketList()
    }


}
