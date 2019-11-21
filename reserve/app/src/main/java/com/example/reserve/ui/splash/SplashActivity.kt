package com.example.reserve.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.reserve.ui.login.LoginActivity
import com.example.reserve.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val h = Handler()
        h.postDelayed(SplashHandler(), 3000)
    }

    inner class SplashHandler : Runnable {
        override fun run() {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }

    }

    override fun onBackPressed() {

    }
}
