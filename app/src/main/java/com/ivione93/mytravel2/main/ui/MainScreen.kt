package com.ivione93.mytravel2.main.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.Center) {
        MyHeader(modifier = modifier)
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Text(
                text = "Alemania",
                color = Color(0xff142D55),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            MyMain()
            MyMain()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Londres",
                color = Color(0xff142D55),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            MyMain()
        }
    }
}


@Composable
fun MyHeader(modifier: Modifier = Modifier) {
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
fun MyMain(title: String = "Alemania") {
    Column() {
        MainCard()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCard(modifier: Modifier = Modifier) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp), onClick = {}, colors = CardDefaults.cardColors(
        containerColor = Color(0xff0856CF).copy(alpha = 0.2f),
        contentColor = Color.Black
    )) {
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
                Text(text = "Explorar Berlín", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color(0xff142D55))
                Row {
                    Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Calendario", tint = Color(0xff142D55))
                    Text(text = "1 al 7 de julio 2023", fontSize = 18.sp, color = Color(0xff142D55))
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