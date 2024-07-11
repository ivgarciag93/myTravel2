package com.ivione93.mytravel2.models

data class TravelModel (
    val countries: List<Country>
)

data class Country (
    val name: String,
    val cities: List<City>
)

data class City (
    val name: String,
    val dates: String,
    val travel: List<Travel>)

data class Travel (
    val day: String,
    val dayWeek: String,
    val places: List<Place>
)

data class Place (
    val name: String,
    val shortDesc: String,
    val desc: String,
    val img: String,
    val maps: String,
    val url: String,
    val schedule: String? = null,
    val price: String? = null
)