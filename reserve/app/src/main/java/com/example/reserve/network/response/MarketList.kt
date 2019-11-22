package com.example.reserve.network.response

import com.example.reserve.network.model.Market
import com.example.reserve.ui.main.MainActivityViewModel
import com.google.gson.annotations.SerializedName

data class MarketList(@SerializedName("market_list") val market : ArrayList<Market>)