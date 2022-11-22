package com.example.wikimvvm.daos

import com.example.wikimvvm.model.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("api/rest_v1/page/random/summary")
    suspend fun getRandomArticle(): Response<ArticleResponse>
}