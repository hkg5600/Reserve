package com.example.reserve.ui.register

import android.app.Application
import androidx.databinding.ObservableField
import com.example.reserve.base.BaseViewModel
import com.example.reserve.network.service.UserService

class RegisterActivityViewModel(application: Application , private val userService: UserService) : BaseViewModel(application) {

    val id = ObservableField<String>()
    val pw = ObservableField<String>()
    val name = ObservableField<String>()
    val type = ObservableField<Int>()
    val phoneNum = ObservableField<String>()

    fun register() = addDisposable(userService.register(id.get()!!, pw.get()!!, name.get()!!, phoneNum.get()!!, type.get()!!), getMsgObserver())
}