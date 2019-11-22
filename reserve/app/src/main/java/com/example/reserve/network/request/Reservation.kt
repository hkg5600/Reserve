package com.example.reserve.network.request

import com.example.reserve.network.model.UserMenu
import com.google.gson.annotations.SerializedName

data class Reservation(@SerializedName("market_id") val marketId: Int, @SerializedName("time") val time: Long,@SerializedName("menu") val menu: ArrayList<UserMenu>)