package com.ivione93.mytravel2

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.ivione93.mytravel2.models.TravelModel
import com.ivione93.mytravel2.screens.CityScreen
import com.ivione93.mytravel2.screens.PlaceScreen
import com.ivione93.mytravel2.screens.SplashScreen
import com.ivione93.mytravel2.screens.TravelScreen
import java.io.InputStreamReader

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val travelModel = loadTravel(LocalContext.current)

    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController = navController)}
        composable("travel") {
            TravelScreen(modifier, travelModel) { city ->
                navController.navigate("city/${city.name}")
            }
        }
        composable(
            "city/{cityName}",
            arguments = listOf(navArgument("cityName") { type = NavType.StringType })
        ) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName")
            val city = travelModel.countries.flatMap { it.cities }.find { it.name == cityName }
            city?.let {
                CityScreen(modifier, it, navController) { place ->
                    navController.navigate("place/${place.name}")
                }
            }
        }
        composable(
            "place/{placeName}",
            arguments = listOf(navArgument("placeName") { type = NavType.StringType })
        ) { backStackEntry ->
            val placeName = backStackEntry.arguments?.getString("placeName")
            val lugar = travelModel.countries
                .flatMap { it.cities }
                .flatMap { it.travel }
                .flatMap { it.places }
                .find { it.name == placeName }
            lugar?.let { PlaceScreen(modifier, it, navController) }
        }
    }
}

fun loadTravel(context: Context): TravelModel {
    val inputStream = context.assets.open("viaje_data.json")
    val reader = InputStreamReader(inputStream)
    return Gson().fromJson(reader, TravelModel::class.java)
}