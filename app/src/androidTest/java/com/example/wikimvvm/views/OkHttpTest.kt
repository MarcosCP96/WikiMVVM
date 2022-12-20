package com.example.wikimvvm.views

import androidx.test.espresso.*
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.core.app.ActivityScenario
import com.example.wikimvvm.library.OkHttp3IdlingResource
import com.example.wikimvvm.model.OkHttpProvider
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After

@RunWith(AndroidJUnit4::class)
@LargeTest
class OkHttpTest {
    private var mockWebServer = MockWebServer()
    private lateinit var okHttp3IdlingResource: OkHttp3IdlingResource
    private val abc = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j")
    private var indexRequest = 0

    @Before
    fun setUp() {
        okHttp3IdlingResource = OkHttp3IdlingResource.create(
            "okhttp",
            OkHttpProvider.instance
        )
        IdlingRegistry.getInstance().register(
            okHttp3IdlingResource
        )
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                indexRequest++
                val letra = abc[indexRequest % 10]
                println("request.sequenceNumber ${indexRequest % 10} -> ${request.path}")
                return when (request.path) {
                    "/api/rest_v1/page/random/summary" ->
                        MockResponse().setBody(
                            "{\"title\":\"${letra}1\",\"thumbnail\":{\"source\":\"${letra}2\"}," + "\"extract\":\"${letra}3\"," + "\"content_urls\":{\"mobile\":{\"page\":\"${letra}4\"}}}"
                        ).setResponseCode(200)
                    else ->
                        MockResponse().setResponseCode(500)
                }

            }
        }

        mockWebServer.start(8080)
        println(OkHttpProvider.baseUrl)
        OkHttpProvider.baseUrl = mockWebServer.url("/").toString()
        println(OkHttpProvider.baseUrl)
        ActivityScenario.launch(MainActivity::class.java)

//        activityRule.scenario.onActivity(ActivityAction<MainActivity> { activity ->
//            decorView = activity.window.decorView
//        })
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(okHttp3IdlingResource)
    }

    @Test
    fun checkIfArticlesAreDisplayed() {
//        mockWebServer.dispatcher = object : Dispatcher() {
//            override fun dispatch(request: RecordedRequest): MockResponse {
//                return MockResponse()
//                    .setResponseCode(200)
//                    .setBody(FileReader.readStringFromFile("success_response.json"))
//            }
//        }
        onView(withText("a1")).check(matches(isDisplayed()))
//        onView(withText("b1")).check(matches(isDisplayed()))
//        onView(withText("c1")).check(matches(isDisplayed()))
//        onView(withText("d1")).check(matches(isDisplayed()))
//        onView(withText("e1")).check(matches(isDisplayed()))
    }
}

