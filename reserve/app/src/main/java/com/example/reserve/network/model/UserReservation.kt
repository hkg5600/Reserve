package com.example.reserve.network.model

import com.google.gson.annotations.SerializedName

data class UserReservation(@SerializedName("order_id") val orderId: Int, @SerializedName("market_name") val marketName: String)