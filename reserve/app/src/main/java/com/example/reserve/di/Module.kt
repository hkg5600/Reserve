package com.example.reserve.di

import com.example.reserve.utils.BASE_URL
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

val networkModule = module {

}

var serviceModel = module {

}

var viewModelPart = module {

}

var adapterPart = module {

}

var repositoryPart = module {
    factory {

    }
}

var myDiModule = listOf(viewModelPart, networkModule, serviceModel, adapterPart, repositoryPart)