package com.kardusinfo.dicodingfootballbaru.api

import android.util.Log
import java.net.URL

class ApiRepository {

    fun doRequest(url: String): String {
        val result = URL(url).readText()
        Log.d("ApiRepository", "Response: $result")
        return result
    }
}