package com.ivione93.mytravel2.routes

sealed class Routes(val route: String) {

    data object TravelScreen: Routes("travel")
    data object CityScreen: Routes("city")
    data object PlaceScreen: Routes("place")
}