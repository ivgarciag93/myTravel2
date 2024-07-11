package com.ivione93.mytravel2.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivione93.mytravel2.models.Place

@Composable
fun PlaceScreen(modifier: Modifier, place: Place) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(text = place.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = place.shortDesc, fontStyle = FontStyle.Italic)
        Text(text = place.desc)
    }
}