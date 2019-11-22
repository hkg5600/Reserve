package com.example.reserve.ui.order

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import com.example.reserve.R
import com.example.reserve.adapter.MenuListAdapter
import com.example.reserve.base.BaseActivity
import com.example.reserve.databinding.ActivityOrderBinding
import com.example.reserve.network.response.MarketInfo
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View


class OrderActivity : BaseActivity<ActivityOrderBinding, OrderActivityViewModel>() {
    override val layoutResourceId = R.layout.activity_order
    override val viewModel: OrderActivityViewModel by viewModel()
    private val menuAdapter: MenuListAdapter by inject()
    lateinit var bottomSheetBehavior : BottomSheetBehavior<*>

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

        viewDataBinding.recyclerViewMenu.run {
            setHasFixedSize(true)
            adapter = menuAdapter
        }
    }

    override fun initObserver() {
        viewModel.data.observe(this, Observer {
            when (it) {
                is MarketInfo -> {
                    viewModel.name.set(it.marketName)
                    menuAdapter.menuList = it.menu
                    menuAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    override fun initListener() {
        menuAdapter.onItemClickListener = object : MenuListAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int, holder: MenuListAdapter.MenuListHolder) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }

        }
    }

    override fun initViewModel() {
        viewModel.getMarketDetail(intent.getIntExtra("marketId", -1))
        viewDataBinding.vm = viewModel
    }


}
