package com.example.kmarket.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.kmarket.R
import kotlin.concurrent.thread

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        thread{
            Thread.sleep(1600)
            startActivity(Intent(this, MainActivity::class.java))
            Animatoo.animateSlideUp(this)
            finish()
        }
    }
}