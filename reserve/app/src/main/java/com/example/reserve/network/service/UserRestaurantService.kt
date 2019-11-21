package com.example.reserve.network.service

import com.example.reserve.network.api.UserRestaurantApi
import com.example.reserve.network.model.UserMenu
import com.example.reserve.network.request.Reservation
import com.example.reserve.network.response.MarketInfo
import com.example.reserve.utils.TokenObject
import io.reactivex.Single

interface UserRestaurantService {
    fun userReservation(marketId: Int, menu: ArrayList<UserMenu>) : Single<retrofit2.Response<Any>>
    fun getMarketInfo(marketId: Int) : Single<retrofit2.Response<MarketInfo>>
}

class UserRestaurantServiceImpl(private val api: UserRestaurantApi) : UserRestaurantService {
    override fun getMarketInfo(marketId: Int) = api.getMarketInfo(TokenObject.token!!, "/api/market/$marketId")

    override fun userReservation(marketId: Int, menu: ArrayList<UserMenu>) = api.userReservation(TokenObject.token!!, Reservation(marketId, menu))

}