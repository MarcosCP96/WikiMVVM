package com.example.wikimvvm.repository

import com.example.wikimvvm.daos.APIService
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.model.Thumbnail
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ArticleRepository {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://es.wikipedia.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getRandomArticle(): ArticleResponse {
//        val randomArticle = ArticleResponse("", Thumbnail(""), "")
//        val call = getRetrofit().create(APIService::class.java).getRandomArticle()
//        val article = call.body()
//        randomArticle.title = article!!.title
//        randomArticle.extract = article.extract
//        randomArticle.thumbnail = article.thumbnail
//        return randomArticle
//        val randomArticle = ArticleResponse("", Thumbnail(""), "")
        val call = getRetrofit().create(APIService::class.java).getRandomArticle()
        val article = call.body()!!
//        randomArticle.title = article!!.title
//        randomArticle.extract = article.extract
//        randomArticle.thumbnail = article.thumbnail
        return article
    }
}