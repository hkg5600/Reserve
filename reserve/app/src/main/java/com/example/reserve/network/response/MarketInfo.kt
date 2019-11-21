package com.example.reserve.network.response

import com.example.reserve.network.model.MarketMenu
import com.google.gson.annotations.SerializedName

data class MarketInfo(@SerializedName("market_id") val marketId: Int, @SerializedName("market_name") val marketName: String, val menu: ArrayList<MarketMenu>)