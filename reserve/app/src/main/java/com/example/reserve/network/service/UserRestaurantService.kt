package com.example.reserve.network.service

import androidx.core.view.MarginLayoutParamsCompat
import com.example.reserve.network.api.UserRestaurantApi
import com.example.reserve.network.model.UserMenu
import com.example.reserve.network.request.MarketName
import com.example.reserve.network.request.Reservation
import com.example.reserve.network.response.MarketInfo
import com.example.reserve.network.response.MarketLIst
import com.example.reserve.utils.TokenObject
import io.reactivex.Single
import retrofit2.Response

interface UserRestaurantService {
    fun userReservation(marketId: Int, menu: ArrayList<UserMenu>) : Single<Response<Any>>
    fun getMarketInfo(marketId: Int) : Single<Response<MarketInfo>>
    fun searchMarket(marketName: String) : Single<Response<MarketLIst>>
}

class UserRestaurantServiceImpl(private val api: UserRestaurantApi) : UserRestaurantService {

    override fun searchMarket(marketName: String) = api.searchMarket(TokenObject.token!!, MarketName(marketName))

    override fun getMarketInfo(marketId: Int) = api.getMarketInfo(TokenObject.token!!, "/api/market/$marketId")

    override fun userReservation(marketId: Int, menu: ArrayList<UserMenu>) = api.userReservation(TokenObject.token!!, Reservation(marketId, menu))

}