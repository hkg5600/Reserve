package com.example.reserve.ui.register

import android.content.Intent
import android.view.WindowManager
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
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
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

        viewModel.message.observe(this, Observer {
            startActivity(Intent(this, LoginActivity::class.java))
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
