package com.example.wikimvvm.model

import okhttp3.OkHttpClient

object OkHttpProvider {
    var baseUrl = "https://es.wikipedia.org/"
    val instance: OkHttpClient = OkHttpClient.Builder().build()
}
