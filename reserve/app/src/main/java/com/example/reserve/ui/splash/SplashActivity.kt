package com.example.reserve.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.reserve.network.response.VerifyToken
import com.example.reserve.ui.login.LoginActivity
import com.example.reserve.ui.main.MainActivity
import com.example.reserve.ui.selector.SelectorActivity
import com.example.reserve.utils.TokenObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.tokenDisposable()

        viewModel.tokenSuccess.observe(this, Observer {
            TokenObject.token?.let {
                viewModel.verifyToken(it)
            }
        })

        viewModel.tokenError.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, SelectorActivity::class.java))
        })

        viewModel.data.observe(this, Observer {
            when (it) {
                is VerifyToken -> {
                    val h = Handler()
                    h.postDelayed(SplashHandler(), 1000)
                }
            }
        })

        viewModel.error.observe(this, Observer {
            startActivity(Intent(this, SelectorActivity::class.java))
        })
    }

    inner class SplashHandler : Runnable {
        override fun run() {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }

    }

    override fun onBackPressed() {

    }
}
