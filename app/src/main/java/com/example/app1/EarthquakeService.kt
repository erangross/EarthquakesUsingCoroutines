package com.example.app1

import okhttp3.Response
import retrofit2.http.GET



interface EarthquakeService {
    @GET("earthquakes/feed/v1.0/summary/all_hour.geojson")
    suspend fun getEarthquakes(): retrofit2.Response<Earthquake>
}

