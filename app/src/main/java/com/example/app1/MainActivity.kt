package com.example.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchEarthquakeListUsingCoroutines()

    }

    /**
     * This function fetches a list of earthquakes using Retrofit and coroutines.
     * It initializes the Retrofit library with the earthquake API base URL and a GSON converter factory.
     * Then, it creates an instance of the Earthquake service and launches a coroutine in the IO dispatcher.
     * The coroutine fetches the earthquake data from the API and logs the ID, magnitude, place, time, and coordinates for each earthquake.
     */

    fun fetchEarthquakeListUsingCoroutines() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(EarthquakeService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getEarthquakes()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.features?.forEach { feature ->
                        Log.d("MainActivity", "ID: ${feature.id}")
                        Log.d("MainActivity", "Magnitude: ${feature.properties.magnitude}")
                        Log.d("MainActivity", "Place: ${feature.properties.place}")
                        Log.d("MainActivity", "Time: ${feature.properties.time}")
                        Log.d("MainActivity", "Type: ${feature.properties.type}")

                        for((index, coordinate) in feature.geometry.coordinates.withIndex()) {
                            Log.d("MainActivity", "Coordinate $index: $coordinate")
                        }
                    }
                }
            }
        }
    }


}



