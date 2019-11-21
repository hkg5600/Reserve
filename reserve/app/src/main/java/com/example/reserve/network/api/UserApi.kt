package com.example.reserve.network.api

import com.example.reserve.network.request.UserLogin
import com.example.reserve.network.response.TokenResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/user/auth")
    fun login(@Body login: UserLogin) : Single<retrofit2.Response<TokenResponse>>
}