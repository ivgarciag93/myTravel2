package com.ivione93.mytravel2.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivione93.mytravel2.R
import com.ivione93.mytravel2.models.City
import com.ivione93.mytravel2.models.TravelModel
import com.ivione93.mytravel2.ui.theme.MyTypography

@Composable
fun TravelScreen(modifier: Modifier, travelModel: TravelModel, onCityClick: (City) -> Unit) {
    Column(verticalArrangement = Arrangement.Center) {
        MyHeader(modifier)
        LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
            items(travelModel.countries) { country ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = country.name,
                    color = Color(0xff142D55),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MyTypography.fontFamily
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
    Box(
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val width = size.width
            val height = size.height

            val path = Path().apply {
                lineTo(0f, height * 0.85f)
                quadraticBezierTo(width * 0.25f, height * 0.95f, width * 0.5f, height * 0.85f)
                quadraticBezierTo(width * 0.75f, height * 0.75f, width, height * 0.90f)
                lineTo(width, 0f)
                close()
            }

            drawPath(
                path = path,
                color = Color(0xff4B92FF),
                style = Fill
            )
        }
        Row(
            modifier = Modifier
                .height(200.dp)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
                Modifier
                    .size(100.dp)
                    .padding(horizontal = 8.dp), tint = Color.White)
            Text(text = "MyTravel", color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Bold, fontFamily = MyTypography.fontFamily)
        }
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
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(text = "Explorar ${city.name}", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color(0xff142D55), fontFamily = MyTypography.fontFamily)
                Row {
                    Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Calendario", tint = Color(0xff142D55))
                    Text(text = city.dates, fontSize = 18.sp, color = Color(0xff142D55), fontFamily = MyTypography.fontFamily)
                }

            }
            IconButton(
                onClick = {},
                modifier = Modifier.size(30.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color(0xff4B92FF),
                    contentColor = Color.White
                )
            ) {
                Icon(imageVector = Icons.Filled.ArrowBackIosNew, modifier = Modifier
                    .rotate(180f)
                    .size(20.dp), contentDescription = "Home")
            }
        }
    }
}