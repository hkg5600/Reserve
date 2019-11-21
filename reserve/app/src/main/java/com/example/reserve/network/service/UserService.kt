package com.example.reserve.network.service

import com.example.reserve.network.api.UserApi
import com.example.reserve.network.request.UserLogin
import com.example.reserve.network.response.TokenResponse
import io.reactivex.Single
import retrofit2.Response

interface UserService {
    fun login(id: String, pw: String) : Single<Response<TokenResponse>>
}

class UserServiceImpl(private val api: UserApi) : UserService {
    override fun login(id: String, pw: String) = api.login(UserLogin(id, pw))
}