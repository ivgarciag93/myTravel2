package com.ivione93.mytravel2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivione93.mytravel2.models.City
import com.ivione93.mytravel2.models.TravelModel

@Composable
fun TravelScreen(modifier: Modifier, travelModel: TravelModel, onCityClick: (City) -> Unit) {
    Column(verticalArrangement = Arrangement.Center) {
        MyHeader(modifier)
        LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
            items(travelModel.countries) { country ->
                Text(
                    text = country.name,
                    color = Color(0xff142D55),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                country.cities.forEach { city ->
                    CityCard(city, onCityClick)
                }
            }
        }
    }
}

@Composable
fun MyHeader(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color(0xff0856CF))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Filled.Place, contentDescription = "Logo", Modifier.size(40.dp), tint = Color.White)
        Text(text = "MyTravel", color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CityCard(city: City, onCityClick: (City) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
        .clickable { onCityClick(city) },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff0856CF).copy(alpha = 0.2f),
            contentColor = Color.Black
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(text = "Explorar ${city.name}", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color(0xff142D55))
                Row {
                    Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Calendario", tint = Color(0xff142D55))
                    Text(text = city.dates, fontSize = 18.sp, color = Color(0xff142D55))
                }

            }
            IconButton(
                onClick = {},
                modifier = Modifier.size(30.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color(0xff0856CF),
                    contentColor = Color.White
                )
            ) {
                Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "Home")
            }
        }
    }
}