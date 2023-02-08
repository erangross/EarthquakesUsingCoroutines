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


//
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
                val earthquake = response.body()
                earthquake?.let {
                    if (it.features.isNotEmpty()) {
                        for (i in it.features.indices) {
                            Log.d("MainActivity", "onCreate: ${it.features[i].id})")
                            Log.d(
                                "MainActivity",
                                "onCreate: ${it.features[i].properties.magnitude}"
                            )
                            Log.d("MainActivity", "onCreate: ${it.features[i].properties.place}")
                            Log.d("MainActivity", "onCreate: ${it.features[i].properties.time}")
                            Log.d("MainActivity", "onCreate: ${it.features[i].properties.type}")
                            if (it.features[i].geometry.coordinates.size >= 3) {
                                Log.d(
                                    "MainActivity",
                                    "onCreate: ${it.features[i].geometry.coordinates[0]}"
                                )
                                Log.d(
                                    "MainActivity",
                                    "onCreate: ${it.features[i].geometry.coordinates[1]}"
                                )
                                Log.d(
                                    "MainActivity",
                                    "onCreate: ${it.features[i].geometry.coordinates[2]}"
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}
}



