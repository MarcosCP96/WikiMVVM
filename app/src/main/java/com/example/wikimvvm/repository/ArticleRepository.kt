package com.example.wikimvvm.repository

import com.example.wikimvvm.daos.APIService
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.model.NetworkRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleRepository(private val networkRequest: NetworkRequest) {
//    private fun getRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://es.wikipedia.org/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

    suspend fun getRandomArticle(): ArticleResponse {
        val call = networkRequest.getApi().getRandomArticle()
        return call.body()!!
    }
}