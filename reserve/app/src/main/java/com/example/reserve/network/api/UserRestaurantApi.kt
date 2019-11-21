package com.example.reserve.network.api

import com.example.reserve.network.request.Reservation
import io.reactivex.Single
import retrofit2.http.Header
import retrofit2.http.POST

interface UserRestaurantApi {

    @POST("/service/reservation")
    fun userReservation(@Header("Authorization") token: String, menu: Reservation) : Single<retrofit2.Response<Any>>
}