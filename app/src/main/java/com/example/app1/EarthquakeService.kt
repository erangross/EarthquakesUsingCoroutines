package com.example.app1

import okhttp3.Response
import retrofit2.http.GET



interface EarthquakeService {
    @GET("/fdsnws/event/1/query?format=geojson&limit=10")
    suspend fun getEarthquakes(): retrofit2.Response<Earthquake>
}

