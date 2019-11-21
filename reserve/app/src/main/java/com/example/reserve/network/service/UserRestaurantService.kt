package com.example.reserve.network.service

import com.example.reserve.network.api.UserRestaurantApi
import com.example.reserve.network.model.Menu
import com.example.reserve.network.request.Reservation
import com.example.reserve.utils.TokenObject
import io.reactivex.Single

interface UserRestaurantService {
    fun userReservation(marketId: Int, menu: ArrayList<Menu>) : Single<retrofit2.Response<Any>>
}

class UserRestaurantServiceImpl(private val api: UserRestaurantApi) : UserRestaurantService {

    override fun userReservation(marketId: Int, menu: ArrayList<Menu>) = api.userReservation(TokenObject.token!!, Reservation(marketId, menu))

}