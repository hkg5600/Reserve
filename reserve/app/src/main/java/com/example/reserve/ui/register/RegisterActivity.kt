package com.example.reserve.ui.register

import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.reserve.R
import com.example.reserve.base.BaseActivity
import com.example.reserve.databinding.ActivityRegisterBinding
import com.example.reserve.network.response.TokenResponse
import com.example.reserve.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterActivityViewModel>() {
    override val layoutResourceId = R.layout.activity_register

    override val viewModel: RegisterActivityViewModel by viewModel()


    override fun initView() {

    }

    override fun initObserver() {
        viewModel.data.observe(this, Observer {
            when (it) {
                is TokenResponse -> {
                    makeToast("회원가입에 성공했습니다!", false)
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        })
    }

    override fun initListener() {

    }

    override fun initViewModel() {
        viewDataBinding.vm = viewModel
    }


}
