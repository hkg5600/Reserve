package com.example.reserve.network.model

import com.google.gson.annotations.SerializedName

data class Market(@SerializedName("market_id") val marketId: Int, val name: String, val location: String, @SerializedName("tel_num") val number: String)