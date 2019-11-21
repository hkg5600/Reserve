package com.example.reserve.network.response

import com.example.reserve.network.model.MarketMenu
import com.google.gson.annotations.SerializedName

data class ReservationDetail(@SerializedName("market_name") val marketName: String, @SerializedName("menu_list") val menuList: ArrayList<MarketMenu>)