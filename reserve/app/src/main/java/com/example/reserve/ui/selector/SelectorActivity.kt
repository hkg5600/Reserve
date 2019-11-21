package com.example.reserve.ui.selector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reserve.R
import com.example.reserve.base.BaseActivity
import com.example.reserve.databinding.ActivitySelectorBinding
import com.example.reserve.ui.login.LoginActivity
import com.example.reserve.ui.register.RegisterActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectorActivity : BaseActivity<ActivitySelectorBinding, SelectorActivityViewModel>() {
    override val layoutResourceId = R.layout.activity_selector
    override val viewModel: SelectorActivityViewModel by viewModel()

    override fun initView() {

    }

    override fun initObserver() {

    }

    override fun initListener() {
        viewDataBinding.buttonRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        viewDataBinding.buttonLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun initViewModel() {

    }


}
