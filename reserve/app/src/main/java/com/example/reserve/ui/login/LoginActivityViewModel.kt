package com.example.reserve.ui.login

import android.app.Application
import androidx.databinding.ObservableField
import com.example.reserve.base.BaseViewModel
import com.example.reserve.network.service.UserService
import com.example.reserve.room.model.Token

class LoginActivityViewModel(application: Application, private val userService: UserService) : BaseViewModel(application) {

    val id = ObservableField<String>()
    val pw = ObservableField<String>()

    fun login() = addDisposable(userService.login(id.get()!!, pw.get()!!), getDataObserver())

    fun insertToken(token : String) = addRoomDisposable(tokenRepository.insertToken(Token(1, token)), "token")
}