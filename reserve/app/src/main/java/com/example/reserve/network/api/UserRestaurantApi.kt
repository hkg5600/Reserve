package com.example.reserve.network.api

import com.example.reserve.network.request.MarketName
import com.example.reserve.network.request.Reservation
import com.example.reserve.network.response.MarketInfo
import com.example.reserve.network.response.MarketList
import com.example.reserve.network.response.ReservationDetail
import com.example.reserve.network.response.ReservationList
import io.reactivex.Single
import retrofit2.http.*

interface UserRestaurantApi {

    @POST("/service/reservation") //유저 예약하기
    fun userReservation(@Header("Authorization") token: String, @Body menu: Reservation) : Single<retrofit2.Response<Any>>

    @GET //매점 상세보기
    fun getMarketInfo(@Header("Authorization") token: String, @Url url: String) : Single<retrofit2.Response<MarketInfo>>

    @POST("/service/markets") //매점 검색하기
    fun searchMarket(@Header("Authorization") token: String, @Body market: MarketName) : Single<retrofit2.Response<MarketList>>

    @GET("/service/reservation") // 유저 예약 목록
    fun getReservationList(@Header("Authorization") token: String) : Single<retrofit2.Response<ReservationList>>

    @GET //예약 상세보기
    fun getDetailReservation(@Header("Authorization") token: String, @Url url: String) : Single<retrofit2.Response<ReservationDetail>>
}