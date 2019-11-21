package com.example.reserve.network.response

import com.example.reserve.network.request.Reservation
import com.google.gson.annotations.SerializedName

data class ReservationList(@SerializedName("order_list") val orderList: ArrayList<Reservation>)