package com.example.reserve.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.reserve.R
import com.example.reserve.base.BaseActivity
import com.example.reserve.databinding.ActivityMainBinding
import com.example.reserve.network.response.UserInfo
import com.example.reserve.utils.UserObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override val layoutResourceId = R.layout.activity_main

    override val viewModel: MainActivityViewModel by viewModel()

    override fun initView() {

    }

    override fun initObserver() {
        viewModel.data.observe(this, Observer {
            when (it) {
                is UserInfo -> {
                    UserObject.userInfo = it
                    viewModel.userMessage.set("${UserObject.userInfo?.name}님 오늘은 어디가시나요?")
                }
            }
        })
    }

    override fun initListener() {
        viewDataBinding.buttonReservation.setOnClickListener {

        }

        viewDataBinding.buttonAddPoint.setOnClickListener {

        }

        viewDataBinding.buttonMyPage.setOnClickListener {

        }

        viewDataBinding.buttonReservationList.setOnClickListener {

        }
    }

    override fun initViewModel() {
        viewDataBinding.vm = viewModel
        viewModel.getUserInfo()
    }


}
