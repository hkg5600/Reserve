package com.example.reserve.ui.login

import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import com.example.reserve.R
import com.example.reserve.base.BaseActivity
import com.example.reserve.databinding.ActivityLoginBinding
import com.example.reserve.network.response.TokenResponse
import com.example.reserve.room.repository.TokenRepository
import com.example.reserve.ui.main.MainActivity
import com.example.reserve.utils.TokenObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginActivityViewModel>() {

    override val layoutResourceId = R.layout.activity_login

    override val viewModel: LoginActivityViewModel by viewModel()

    override fun initView() {

    }

    override fun initObserver() {
        viewModel.data.observe(this, Observer {
            when (it) {
                is TokenResponse -> {
                    makeToast(it.token, true)
                    viewModel.insertToken(it.token)
                    TokenObject.token = it.token
                }
            }
        })

        viewModel.roomSuccess.observe(this, Observer {
            when(it) {
                "token" -> startActivity(Intent(this, MainActivity::class.java))
            }
        })

        viewModel.error.observe(this, Observer {
            Log.d("Observe Error: ", it)
            when(it) {
                "room failed" -> makeToast("오류 발생 잠시후 시도해주세요", false)
            }
        })
    }

    override fun initListener() {
        viewDataBinding.imageButtonBack.setOnClickListener {
            finish()
        }
    }

    override fun initViewModel() {
        viewDataBinding.vm = viewModel
    }


}
