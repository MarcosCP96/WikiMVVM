package com.example.wikimvvm.repository

import com.example.wikimvvm.daos.APIService
import com.example.wikimvvm.model.ArticleResponse
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
        val call = getRetrofit().create(APIService::class.java).getRandomArticle()
        return call.body()!!
    }
}