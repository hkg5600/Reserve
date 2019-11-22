package com.example.reserve.ui.order

import android.annotation.SuppressLint
import android.app.Application
import androidx.databinding.ObservableField
import com.example.reserve.base.BaseViewModel
import com.example.reserve.network.model.MarketMenu
import com.example.reserve.network.model.UserMenu
import com.example.reserve.network.service.UserRestaurantService
import java.time.LocalDateTime
import java.time.ZoneOffset

class OrderActivityViewModel(application: Application, private val service: UserRestaurantService) : BaseViewModel(application) {
    var orderList : ArrayList<UserMenu> = ArrayList()
    val name = ObservableField<String>()
    var marketId = 0
    var dateTime : LocalDateTime? = null

    fun getMarketDetail(marketId: Int) = addDisposable(service.getMarketInfo(marketId), getDataObserver())
    @SuppressLint("NewApi")
    fun applyReservation() = addDisposable(service.userReservation(marketId, dateTime?.toInstant(ZoneOffset.ofTotalSeconds(0))?.toEpochMilli()!!, orderList), getMsgObserver())
}