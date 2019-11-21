package com.example.reserve.network.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(val id: String, val pw: String, val name: String, @SerializedName("phone_num") val phoneNum: String, val type: Int)