package com.example.reserve.ui.main

import android.app.Application
import androidx.databinding.ObservableField
import com.example.reserve.base.BaseViewModel
import com.example.reserve.network.service.UserService

class MainActivityViewModel(application: Application, private val userService: UserService) : BaseViewModel(application) {
    val userMessage = ObservableField<String>()

    fun getUserInfo() = addDisposable(userService.getUserInfo(), getDataObserver())
}