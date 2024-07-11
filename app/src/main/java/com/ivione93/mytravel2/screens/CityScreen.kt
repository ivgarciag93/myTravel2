package com.ivione93.mytravel2.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Place
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ivione93.mytravel2.models.City
import com.ivione93.mytravel2.models.Place

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityScreen(modifier: Modifier, city: City, navController: NavController, onPlaceClick: (Place) -> Unit) {

    var daySelected by remember { mutableStateOf("0") }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "back")
                    }
                },
                title = { Text(city.name) },
                actions = {
                    Icon(imageVector = Icons.Default.Place, contentDescription = "booking", modifier = Modifier.padding(end = 8.dp))
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
                            containerColor = if(daySelected == day.day) Color(0xff0856CF) else Color(0xff0856CF).copy(alpha = 0.2f),
                            contentColor = if(daySelected == day.day) Color.White else Color(0xff142D55)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column(modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = day.dayWeek)
                            Text(text = day.day, fontWeight = FontWeight.Bold, fontSize = 20.sp)
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
                    containerColor = Color.Black.copy(alpha = 0.5f)
                ),
                shape = RoundedCornerShape(8.dp),
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = place.name,
                        color = Color.White,
                        fontWeight = FontWeight.Bold, fontSize = 20.sp
                    )
                    Text(
                        text = place.shortDesc,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ImageCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box() {
            Image(
                painter = rememberAsyncImagePainter(model = "https://concepto.de/wp-content/uploads/2020/12/imagen-e1607991781485.jpg"),
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
                    containerColor = Color.Black.copy(alpha = 0.5f)
                ),
                shape = RoundedCornerShape(8.dp),
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "Titulo",
                        color = Color.White
                    )
                    Text(
                        text = "Subtitulo",
                        color = Color.White
                    )
                }
            }
        }
    }
}