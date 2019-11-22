package com.example.reserve.network.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("id") val id: String, @SerializedName("pw") val pw: String, val name: String, @SerializedName(
        "phone_num"
    ) val phoneNum: String, @SerializedName(
        "type"
    ) val type: Int
)