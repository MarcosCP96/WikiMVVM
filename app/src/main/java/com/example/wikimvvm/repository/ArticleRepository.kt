package com.example.wikimvvm.repository

import com.example.wikimvvm.`interface`.APIService
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.model.Thumbnail
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ArticleRepository {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://en.wikipedia.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRandomArticle(): ArticleResponse {
        val randomArticle = ArticleResponse("", Thumbnail(""), "")
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getRandomArticle()
            val article = call.body()
            randomArticle.title = article!!.title
            randomArticle.extract = article.extract
            randomArticle.thumbnail = article.thumbnail
        }
        return randomArticle
    }
}