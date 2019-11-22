package com.example.reserve.network.service

import com.example.reserve.network.api.UserRestaurantApi
import com.example.reserve.network.model.UserMenu
import com.example.reserve.network.request.MarketName
import com.example.reserve.network.request.Reservation
import com.example.reserve.network.response.MarketInfo
import com.example.reserve.network.response.MarketList
import com.example.reserve.network.response.ReservationDetail
import com.example.reserve.network.response.ReservationList
import com.example.reserve.utils.TokenObject
import io.reactivex.Single
import retrofit2.Response

interface UserRestaurantService {
    fun userReservation(marketId: Int, time: Long, menu: ArrayList<UserMenu>) : Single<Response<Any>>
    fun getMarketInfo(marketId: Int) : Single<Response<MarketInfo>>
    fun searchMarket(marketName: String) : Single<Response<MarketList>>
    fun getReservationList() : Single<Response<ReservationList>>
    fun getDetailReservation(orderId: Int) : Single<Response<ReservationDetail>>
}

class UserRestaurantServiceImpl(private val api: UserRestaurantApi) : UserRestaurantService {
    override fun getDetailReservation(orderId: Int) = api.getDetailReservation(TokenObject.token!!, "/service/reservation/$orderId")

    override fun getReservationList() = api.getReservationList(TokenObject.token!!)

    override fun searchMarket(marketName: String) = api.searchMarket(TokenObject.token!!, MarketName(marketName))

    override fun getMarketInfo(marketId: Int) = api.getMarketInfo(TokenObject.token!!, "/service/market/$marketId")

    override fun userReservation(marketId: Int, time: Long,menu: ArrayList<UserMenu>) = api.userReservation(TokenObject.token!!, Reservation(marketId, time ,menu))

}