package com.example.reserve.di

import com.example.reserve.network.api.UserApi
import com.example.reserve.network.api.UserRestaurantApi
import com.example.reserve.network.response.TokenResponse
import com.example.reserve.network.service.UserRestaurantService
import com.example.reserve.network.service.UserRestaurantServiceImpl
import com.example.reserve.network.service.UserService
import com.example.reserve.network.service.UserServiceImpl
import com.example.reserve.room.repository.TokenRepository
import com.example.reserve.ui.login.LoginActivityViewModel
import com.example.reserve.ui.main.MainActivityViewModel
import com.example.reserve.utils.BASE_URL
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofit: Retrofit = Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

private val userApi: UserApi = retrofit.create(UserApi::class.java)
private val userRestaurantApi: UserRestaurantApi = retrofit.create(UserRestaurantApi::class.java)

val networkModule = module {
    single { userApi }
    single { userRestaurantApi }
}

var serviceModel = module {
    factory<UserService> { UserServiceImpl(get()) }
    factory<UserRestaurantService> { UserRestaurantServiceImpl(get()) }
}

var viewModelPart = module {
    viewModel { MainActivityViewModel(get()) }
    viewModel { LoginActivityViewModel(get(), get()) }
}

var adapterPart = module {

}

var repositoryPart = module {
    factory { TokenRepository(get()) }
}

var myDiModule = listOf(viewModelPart, networkModule, serviceModel, adapterPart, repositoryPart)