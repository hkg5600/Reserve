package com.example.reserve.network.response

import com.google.gson.annotations.SerializedName

data class UserInfo(val id: String, val name: String, val point: Int, @SerializedName("phone_num") val phoneNum : String)