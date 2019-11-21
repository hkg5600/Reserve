package com.example.reserve.network.service

import com.example.reserve.network.api.UserApi
import com.example.reserve.network.request.UserLogin
import com.example.reserve.network.request.UserPoint
import com.example.reserve.network.response.TokenResponse
import com.example.reserve.network.response.UserInfo
import com.example.reserve.utils.TokenObject
import io.reactivex.Single
import retrofit2.Response

interface UserService {
    fun login(id: String, pw: String) : Single<Response<TokenResponse>>
    fun getUserInfo() : Single<Response<UserInfo>>
    fun addUserPoint(point: Int) : Single<Response<Any>>
}

class UserServiceImpl(private val api: UserApi) : UserService {

    override fun addUserPoint(point: Int) = api.addUserPoint(TokenObject.token!!, UserPoint(point))

    override fun getUserInfo() = api.getUserInfo(TokenObject.token!!)

    override fun login(id: String, pw: String) = api.login(UserLogin(id, pw))
}