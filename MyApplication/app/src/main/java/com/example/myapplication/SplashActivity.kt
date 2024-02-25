package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.myapplication.model.User
import com.example.myapplication.utils.SharedPreference

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreference = SharedPreference ( this)
        val session=sharedPreference.getValueInt ("session" )

        Handler(Looper.getMainLooper()).postDelayed({

            if (session==0) {
                val changePage = Intent(this, MainActivity::class.java)
                startActivity(changePage)
                finish()
            } else {
                val changePage = Intent(this, MenuActivity::class.java)
                startActivity(changePage)
                finish()
            }
        }, 2000)
    }
}