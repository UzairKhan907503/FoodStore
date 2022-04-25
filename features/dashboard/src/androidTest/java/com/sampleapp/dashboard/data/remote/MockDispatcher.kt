package com.sampleapp.dashboard.data.remote

import com.sampleapp.dashboard.data.local.MockResponse.getValidJsonResponse
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest


internal class MockDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            MockNetworkConfig.baseUrl -> {
                MockResponse().setResponseCode(MockNetworkConfig.status)
                    .setBody(getValidJsonResponse())
            }

            else -> throw IllegalArgumentException("Unable to find ${request.path}")
        }
    }
}

