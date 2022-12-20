package com.example.wikimvvm.model

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class ApiDispatcher() : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when(request.path) {
            "/" ->
                MockResponse().setResponseCode(200).setBody("{}");
            else ->
                MockResponse().setResponseCode(404)
        }
    }
}