package com.example.reserve.network.model

import com.google.gson.annotations.SerializedName

data class Order(@SerializedName("order_id") val orderId: Int, @SerializedName("market_name") val marketName: String, @SerializedName("main_menu") val mainMenu: String)