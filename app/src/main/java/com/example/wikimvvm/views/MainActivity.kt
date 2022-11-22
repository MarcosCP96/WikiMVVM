package com.example.wikimvvm.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.room.Room
import com.example.wikimvvm.R
import com.example.wikimvvm.repository.ArticleDatabase
import com.example.wikimvvm.viewmodel.ArticleViewModel

class MainActivity : AppCompatActivity() {
    private val articleViewModel: ArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            ArticleDatabase::class.java, "favouriteArticleDatabase"
        ).build()
        val articleDao = db.articleDao()
        articleViewModel.receiveDAO(articleDao)
    }
}