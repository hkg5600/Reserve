package com.example.reserve.ui.reservation_list

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.example.reserve.R
import com.example.reserve.adapter.MarketListAdapter
import com.example.reserve.adapter.ReservationListAdapter
import com.example.reserve.base.BaseActivity
import com.example.reserve.databinding.ActivityReservationListBinding
import com.example.reserve.network.response.ReservationDetail
import com.example.reserve.network.response.ReservationList
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.app_bar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReservationListActivity :
    BaseActivity<ActivityReservationListBinding, ReservationListActivityViewModel>() {
    override val layoutResourceId = R.layout.activity_reservation_list
    override val viewModel: ReservationListActivityViewModel by viewModel()
    private val orderAdapter: ReservationListAdapter by inject()
    lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    override fun initView() {
        bottomSheetBehavior = BottomSheetBehavior.from(viewDataBinding.bottomSheet)
        bottomSheetBehavior.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {

            @SuppressLint("SwitchIntDef")
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // React to dragging events
            }
        })
        title = "예약목록"
        setSupportActionBar(toolbar)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.back_button)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewDataBinding.recyclerViewOrder.run {
            setHasFixedSize(true)
            adapter = orderAdapter
        }
    }

    override fun initObserver() {
        viewModel.data.observe(this, Observer {
            when (it) {
                is ReservationList -> {
                    orderAdapter.orderList = it.orderList
                    orderAdapter.notifyDataSetChanged()
                }

                is ReservationDetail -> {
                    viewDataBinding.textViewName.text = it.marketName
                    viewDataBinding.textViewMenu.text = it.menuList[0].menuName
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        })
    }

    override fun initListener() {
        orderAdapter.onItemClickListener = object : ReservationListAdapter.OnItemClickListener {
            override fun onClick(
                view: View,
                position: Int,
                holder: ReservationListAdapter.ReservationListHolder
            ) {
                makeToast("${orderAdapter.orderList[position].orderId}", false)
                viewModel.getDetail(orderAdapter.orderList[position].orderId)
            }
        }
    }

    override fun initViewModel() {
        viewDataBinding.vm = viewModel
        viewModel.getReservationList()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
