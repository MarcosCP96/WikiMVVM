package com.example.wikimvvm.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wikimvvm.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}