package com.example.reserve.ui.order

import android.app.Application
import androidx.databinding.ObservableField
import com.example.reserve.base.BaseViewModel
import com.example.reserve.network.service.UserRestaurantService

class OrderActivityViewModel(application: Application, private val service: UserRestaurantService) : BaseViewModel(application) {

    val name = ObservableField<String>()

    fun getMarketDetail(marketId: Int) = addDisposable(service.getMarketInfo(marketId), getDataObserver())
}