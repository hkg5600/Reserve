package com.example.reserve.ui.splash

import android.app.Application
import com.example.reserve.base.BaseViewModel
import com.example.reserve.network.service.UserService

class SplashActivityViewModel(application: Application, private val userService: UserService) :
    BaseViewModel(application) {

    fun verifyToken(token: String) = addDisposable(userService.verifyToken(token), getDataObserver())

}