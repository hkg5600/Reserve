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
import com.example.reserve.network.model.UserMenu
import java.time.LocalDateTime
import java.time.Month
import android.app.TimePickerDialog
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.util.*


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

        viewModel.success.observe(this, Observer {
            finish()
        })
    }

    override fun initListener() {
        menuAdapter.onItemClickListener = object : MenuListAdapter.OnItemClickListener {
            @SuppressLint("SetTextI18n", "NewApi")
            override fun onClick(view: View, position: Int, holder: MenuListAdapter.MenuListHolder) {
                viewDataBinding.textViewTime.setOnClickListener {
                    val cal = Calendar.getInstance()
                    val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                        cal.set(Calendar.HOUR_OF_DAY, hour)
                        cal.set(Calendar.MINUTE, minute)
                        viewModel.dateTime = LocalDateTime.of(viewDataBinding.datePicker.year, viewDataBinding.datePicker.month, viewDataBinding.datePicker.dayOfMonth, hour, minute)
                    }
                    TimePickerDialog(this@OrderActivity, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
                }
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                viewDataBinding.numberPicker.value = 0
                viewDataBinding.textViewMenuDetailName.text = menuAdapter.menuList[position].menuName
                viewDataBinding.textViewMenuDetailDes.text = menuAdapter.menuList[position].menuDes
                viewDataBinding.textViewMenuDetailPrice.text = "${menuAdapter.menuList[position].menuPrice}원"
                viewDataBinding.numberPicker.minValue = 0
                viewDataBinding.numberPicker.maxValue = 57
                viewDataBinding.buttonCheck.setOnClickListener {
                    if (viewModel.orderList.any { it.name == menuAdapter.menuList[position].menuName }) {
                        viewModel.orderList.remove(UserMenu(menuAdapter.menuList[position].menuName, menuAdapter.menuList[position].menuPrice))
                    }
                    for (index: Int in 0 until viewDataBinding.numberPicker.value) {
                        viewModel.orderList.add(UserMenu(menuAdapter.menuList[position].menuName, menuAdapter.menuList[position].menuPrice))
                    }
                    var price = 0
                    viewModel.orderList.forEach {
                        price += it.price
                    }
                    viewDataBinding.textViewPrice.text = "${price}원"
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

                }

                viewDataBinding.numberPicker.setOnValueChangedListener { numberPicker, i, i2 ->
                    viewDataBinding.textViewTotalPrice.text = "${(i2 * menuAdapter.menuList[position].menuPrice)}원"
                }

            }

        }
    }

    override fun initViewModel() {
        viewModel.marketId = intent.getIntExtra("marketId", -1)
        viewModel.getMarketDetail(intent.getIntExtra("marketId", -1))
        viewDataBinding.vm = viewModel
    }


}
