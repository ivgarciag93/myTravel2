package com.ivione93.mytravel2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ivione93.mytravel2.R
import com.ivione93.mytravel2.ui.theme.MyTypography
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.popBackStack()
        navController.navigate("travel")
    }
    MyTravelSplashScreen()
}

@Composable
fun MyTravelSplashScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xff4B92FF)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(64.dp))
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "logo",
            modifier = Modifier.size(150.dp, 150.dp))
        Text(text = "MyTravel",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = MyTypography.fontFamily)
        Spacer(modifier = Modifier.height(128.dp))
        Text(text = "Desarrollado por",
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = MyTypography.fontFamily)
        Text(text = "Iván García Gómez",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = MyTypography.fontFamily)
    }
}

@Preview
@Composable
fun MyTravelSplashScreenPreview() {
    MyTravelSplashScreen()
}