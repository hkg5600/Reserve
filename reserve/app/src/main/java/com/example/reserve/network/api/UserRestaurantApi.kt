package com.example.reserve.network.api

import com.example.reserve.network.request.Reservation
import com.example.reserve.network.response.MarketInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url

interface UserRestaurantApi {

    @POST("/service/reservation")
    fun userReservation(@Header("Authorization") token: String, menu: Reservation) : Single<retrofit2.Response<Any>>

    @GET
    fun getMarketInfo(@Header("Authorization") token: String, @Url url: String) : Single<retrofit2.Response<MarketInfo>>
}