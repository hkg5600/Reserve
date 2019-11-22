package com.example.reserve.ui.reservation_list

import android.app.Application
import com.example.reserve.base.BaseViewModel
import com.example.reserve.network.service.UserRestaurantService

class ReservationListActivityViewModel(application: Application,  private val service: UserRestaurantService) : BaseViewModel(application) {

    fun getReservationList() = addDisposable(service.getReservationList(), getDataObserver())

    fun getDetail(orderId: Int) = addDisposable(service.getDetailReservation(orderId), getDataObserver())

}