package com.example.reserve.network

data class Response<T>(val data: T, var status: Int, val message: String)