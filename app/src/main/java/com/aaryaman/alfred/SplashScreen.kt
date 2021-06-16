package com.aaryaman.alfred

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val hand= Handler()
        hand.postDelayed(
                {
                    startActivity(Intent(this, MainActivity::class.java))
//                    CustomIntent.customType(this, "fadein-to-fadeout")
                }, 1000
        )
    }
}