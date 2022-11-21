package com.example.wikimvvm.repository

import com.example.wikimvvm.`interface`.APIService
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.model.Thumbnail
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Thread.sleep

object ArticleRepository {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://en.wikipedia.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRandomArticle(): ArticleResponse {
        val randomArticle = ArticleResponse("", Thumbnail(""), "")
        runBlocking {
            val call = getRetrofit().create(APIService::class.java).getRandomArticle()
            val article = call.body()
            randomArticle.title = article!!.title
            randomArticle.extract = article.extract
            randomArticle.thumbnail = article.thumbnail
        }
        return randomArticle
    }
}