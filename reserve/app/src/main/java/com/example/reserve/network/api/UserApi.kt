package com.example.reserve.network.api

import com.example.reserve.network.request.UserLogin
import com.example.reserve.network.request.UserPoint
import com.example.reserve.network.response.TokenResponse
import com.example.reserve.network.response.UserInfo
import io.reactivex.Single
import retrofit2.http.*

interface UserApi {

    @POST("/user/auth")
    fun login(@Body login: UserLogin) : Single<retrofit2.Response<TokenResponse>>

    @GET("/user/info")
    fun getUserInfo(@Header("Authorization") token : String) : Single<retrofit2.Response<UserInfo>>

    @PUT("/user/point")
    fun addUserPoint(@Header("Authorization") token : String, @Body point: UserPoint) : Single<retrofit2.Response<Any>>
}