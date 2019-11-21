package com.example.reserve.network.request

import com.example.reserve.network.model.Menu

data class Reservation(val marketId: Int, val menu: ArrayList<Menu>)