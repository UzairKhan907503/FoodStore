package com.sampleapp.dashboard.data.local

import java.io.File

object MockResponse {

    fun getValidJsonResponse(): String {
        val data = MockResponse.javaClass.getResource("server_response.json")!!
        val file = File(data.path)
        return String(file.readBytes())
    }
}