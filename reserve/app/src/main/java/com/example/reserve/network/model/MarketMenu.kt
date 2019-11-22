package com.example.reserve.network.model

import com.google.gson.annotations.SerializedName

data class MarketMenu(@SerializedName("menu_name") val menuName: String, @SerializedName("menu_des") val menuDes: String, @SerializedName("menu_price") val menuPrice: Int)