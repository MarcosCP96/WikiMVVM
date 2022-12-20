package com.example.wikimvvm.model

import com.example.wikimvvm.daos.APIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkRequest(
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(OkHttpProvider.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpProvider.instance)
        .build()
) {
    fun getApi(): APIService {
        return retrofit.create(APIService::class.java)
    }
}