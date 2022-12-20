package com.example.wikimvvm

import com.example.wikimvvm.model.*
import com.example.wikimvvm.repository.ArticleRepository
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertEquals
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class CallTest {
    private val mockWebServer = MockWebServer()
    private val retrofit = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val networkRequest = NetworkRequest(retrofit)
    private val articleRepository = ArticleRepository(networkRequest)

    @Before
    fun setup() {
        mockWebServer.start(8080)
    }

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }

    @Test
    fun test() {
        val mockArticle = ArticleResponse("a1", Thumbnail("a2"), "a3", ContentURLs(Mobile("a4")))
        mockWebServer.enqueue(
            MockResponse()
                .setBody(Gson().toJson(mockArticle))
                .addHeader("Content-Type", "application/json")
        )
        runBlocking {
            val response = articleRepository.getRandomArticle()
            assertEquals(mockArticle, response)
        }
            println(Gson().toJson(mockArticle))

    }

    @Test
    fun testError() {
        val mockArticle = ArticleResponse("a1", Thumbnail("a2"), "a3", ContentURLs(Mobile("a4")))
        mockWebServer.enqueue(
            MockResponse().setResponseCode(500)
                .setBody(Gson().toJson(mockArticle))
                .addHeader("Content-Type", "application/json")
        )
        runBlocking {
            val response = articleRepository.getRandomArticle()
            assertEquals(mockArticle, response)
        }
    }

    @Test
    fun test3() {
        val mockArticle = ArticleResponse ("a1", Thumbnail("a2"), "a3", ContentURLs(Mobile("a4")))
        mockWebServer.enqueue(
        MockResponse().setResponseCode(500)
            .setBody(Gson().toJson(mockArticle))
            .addHeader("Content-Type", "application/json")
        )
    }
}