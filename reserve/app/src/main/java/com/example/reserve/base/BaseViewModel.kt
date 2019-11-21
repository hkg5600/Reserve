package com.example.reserve.base

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.reserve.network.Response
import com.example.reserve.utils.SingleLiveEvent
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseViewModel(val application: Application) : ViewModel() {
    //val tokenRepository = TokenRepository(application)
    val success: SingleLiveEvent<Any> = SingleLiveEvent()
    private val compositeDisposable = CompositeDisposable()
    val roomSuccess: SingleLiveEvent<String> = SingleLiveEvent()
    val error: SingleLiveEvent<String> = SingleLiveEvent()
    val message: SingleLiveEvent<String> = SingleLiveEvent()
    val data: SingleLiveEvent<Any> = SingleLiveEvent()

    fun addDisposable(disposable: Single<*>, observer: DisposableSingleObserver<Any>) {
        compositeDisposable.add(
            disposable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(observer)
        )
    }

    fun addRoomDisposable(disposable: Completable, msg: String) {
        compositeDisposable.add(
            disposable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    roomSuccess.value = msg
                },
                { error.value = "failed" })
        )
    }

    fun getMsgObserver() = MsgDisposableSingleObserver()
    fun getDataObserver() = DataDisposableSingleObserver()

    inner class MsgDisposableSingleObserver : DisposableSingleObserver<Any>() {

        override fun onSuccess(t: Any) = filterResponseWithMsg(t)

        override fun onError(e: Throwable) {
            Log.d("Error Msg", "${e.message}")
            error.value = "failed to connect"
        }

    }

    inner class DataDisposableSingleObserver : DisposableSingleObserver<Any>() {

        override fun onSuccess(t: Any) = filterResponseWithData(t)

        override fun onError(e: Throwable) {
            Log.d("Error Data", "${e.message}")
            error.value = "failed to connect"
        }

    }

//    fun tokenDisposable() {
//        compositeDisposable.add(
//            tokenRepository.getToken().subscribeOn(Schedulers.io()).observeOn(
//                AndroidSchedulers.mainThread()
//            ).subscribeWith(TokenDisposableSingleObserver())
//        )
//    }

//    inner class TokenDisposableSingleObserver : DisposableSingleObserver<Token>() {
//        override fun onSuccess(t: Token) {
//            Log.d("Success", "${t.token}")
//            TokenObject.token = t.token
//            success.call()
//        }
//
//        override fun onError(e: Throwable) {
//            Log.d("Error", "${e.message}")
//            success.call()
//        }
//    }

    fun filterResponseWithMsg(t: Any) {
        t as retrofit2.Response<Response<String>>
        if (t.isSuccessful) {
            if (t.body()?.status == 200)
                message.value = t.body()?.message!!
            else {
                Log.d("Status", t.body()?.status.toString())
                error.value = t.body()?.message
            }
        } else {
            Log.d("Error Body", t.errorBody().toString())
            error.value = t.message()
        }
    }

    fun filterResponseWithData(t: Any) {
        t as retrofit2.Response<*>
        if (t.isSuccessful) {
            if (t.code() == 200) {
                data.value = t.body()
            } else {
                Log.d("Response Error: ", t.errorBody().toString())
                error.value = t.code().toString()
            }
        } else {
            Log.d("Is not Successful: ", t.errorBody().toString())
            error.value = t.message()
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun makeToast(msg: String) {
        Toast.makeText(application.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

}