package com.sampleapp.dashboard.data.remote

import com.sampleapp.remote.utils.ApiUtils
import java.net.HttpURLConnection

object MockNetworkConfig {

    var baseUrl = ApiUtils.BASE_URL


    var status = HttpURLConnection.HTTP_OK


}