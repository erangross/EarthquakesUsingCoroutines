package com.example.app1

//
data class Earthquake(
    val type: String,
    val features: List<Feature>
)

data class Feature(
    val id: String,
    val properties: Properties,
    val geometry: Geometry
)

data class Properties(
    val magnitude: Double,
    val place: String,
    val time: Long,
    val type: String
)

data class Geometry(
    val type: String,
    val coordinates: List<Double>
)

