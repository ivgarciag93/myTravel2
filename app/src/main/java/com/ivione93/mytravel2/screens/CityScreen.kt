package com.ivione93.mytravel2.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Bed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ivione93.mytravel2.models.City
import com.ivione93.mytravel2.models.Place
import com.ivione93.mytravel2.ui.theme.MyTypography

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityScreen(modifier: Modifier, city: City, navController: NavController, onPlaceClick: (Place) -> Unit) {

    var daySelected by remember { mutableStateOf("0") }
    val context = LocalContext.current
    val mapsIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("geo:${city.booking}?q=${city.booking}")) }
    mapsIntent.setPackage("com.google.android.apps.maps")

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Outlined.ArrowBackIosNew, contentDescription = "back", Modifier.size(16.dp), tint = Color(0xff142D55))
                    }
                },
                title = { Text(city.name, color = Color(0xff142D55), fontFamily = MyTypography.fontFamily) },
                actions = {
                    IconButton(onClick = {
                        context.startActivity(mapsIntent)
                    }) {
                        Icon(imageVector = Icons.Outlined.Bed, contentDescription = "back", Modifier.size(24.dp), tint = Color(0xff142D55))
                    }
                }
            )
        }
    ) {
        Column(modifier = modifier) {
            LazyRow(modifier = Modifier.padding(top = 70.dp),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                items(city.travel) { day ->
                    Card(modifier = Modifier
                        .size(height = 75.dp, width = 85.dp)
                        .padding(start = 8.dp)
                        .clickable { daySelected = day.day },
                        colors = CardDefaults.cardColors(
                            containerColor = if(daySelected == day.day) Color(0xff4B92FF) else Color(0xff0856CF).copy(alpha = 0.2f),
                            contentColor = if(daySelected == day.day) Color.White else Color(0xff142D55)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column(modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = day.dayWeek, fontFamily = MyTypography.fontFamily)
                            Text(text = day.day, fontWeight = FontWeight.Bold, fontSize = 20.sp, fontFamily = MyTypography.fontFamily)
                        }
                    }
                }
            }
            LazyColumn(modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
                items(city.travel) { day ->
                    if(daySelected == day.day) {
                        day.places.forEach { place ->
                            PlaceCard(place, onPlaceClick)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PlaceCard(place: Place, onPlaceClick: (Place) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
        .clickable { onPlaceClick(place) },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff0856CF).copy(alpha = 0.2f),
            contentColor = Color.Black,
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box() {
            Image(
                painter = rememberAsyncImagePainter(model = place.img),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Card(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black.copy(alpha = 0.4f)
                ),
                shape = RoundedCornerShape(8.dp),
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = place.name,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontFamily = MyTypography.fontFamily
                    )
                    Text(
                        text = place.shortDesc,
                        color = Color.White,
                        fontFamily = MyTypography.fontFamily
                    )
                }
            }
            if (place.price != null) {
                Card(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xff0856CF).copy(alpha = 0.4f)
                    ),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = place.price,
                            color = Color.White,
                            fontFamily = MyTypography.fontFamily
                        )
                    }
                }
            }
        }
    }
}