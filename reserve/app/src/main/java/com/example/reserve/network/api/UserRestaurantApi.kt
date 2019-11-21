package com.example.reserve.network.api

import com.example.reserve.network.request.MarketName
import com.example.reserve.network.request.Reservation
import com.example.reserve.network.response.MarketInfo
import com.example.reserve.network.response.MarketLIst
import io.reactivex.Single
import retrofit2.http.*

interface UserRestaurantApi {

    @POST("/service/reservation")
    fun userReservation(@Header("Authorization") token: String, menu: Reservation) : Single<retrofit2.Response<Any>>

    @GET
    fun getMarketInfo(@Header("Authorization") token: String, @Url url: String) : Single<retrofit2.Response<MarketInfo>>

    @POST("/service/markets")
    fun searchMarket(@Header("Authorization") token: String, @Body market: MarketName) : Single<retrofit2.Response<MarketLIst>>
}