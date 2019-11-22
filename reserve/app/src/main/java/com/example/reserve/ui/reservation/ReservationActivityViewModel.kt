package com.example.reserve.ui.reservation

import android.app.Application
import com.example.reserve.base.BaseViewModel
import com.example.reserve.network.service.UserRestaurantService

class ReservationActivityViewModel(application: Application, private val service: UserRestaurantService) : BaseViewModel(application) {

    fun getMarketList(name: String) = addDisposable(service.searchMarket(name), getDataObserver())

}