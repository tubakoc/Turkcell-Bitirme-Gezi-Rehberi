package com.example.gezirehberim.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.gezirehberim.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        splashGoster()
    }

    private fun splashGoster() {

        Handler().postDelayed({

            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)

        },2500)
    }
}