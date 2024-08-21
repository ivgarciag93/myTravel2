package com.ivione93.mytravel2.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.CompareArrows
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.AirplanemodeActive
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Directions
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ivione93.mytravel2.R
import com.ivione93.mytravel2.models.Place
import com.ivione93.mytravel2.models.TravelInfo
import com.ivione93.mytravel2.ui.theme.MyTypography
import java.io.InputStream

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceScreen(modifier: Modifier, place: Place, navController: NavController) {

    val context = LocalContext.current
    val infoIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(place.url)) }
    val mapsIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("geo:${place.maps}?q=${place.maps}")) }
    mapsIntent.setPackage("com.google.android.apps.maps")

    // Airports info
    val fromMadridBerlin = TravelInfo("1 de julio", "3h 05min", "Origen", "MAD", "Barajas, T4", "15:55h")
    val toBerlinMadrid = TravelInfo("1 de julio", "3h 05min", "Destino", "BER", "Branderburgo, T1", "19:00h")

    val fromBerlinMunich = TravelInfo("7 de julio", "1h 10min", "Origen", "BER", "Branderburgo, T1", "12:50h")
    val toMunichBerlin = TravelInfo("7 de julio", "1h 10min", "Llegada", "MUN", "FJ Strauss, T2", "14:00h")

    val fromMunichMadrid = TravelInfo("11 de julio", "2h 40min", "Origen", "MUN", "FJ Strauss, T2", "12:00h")
    val toMadridMunich = TravelInfo("7 de julio", "2h 40min", "Llegada", "MAD", "Barajas, T2", "14:40h")

    val fromMadridLondon = TravelInfo("14 de agosto", "1h 30min", "Origen", "MAD", "Barajas, T4S", "08:00h")
    val toLondonMadrid = TravelInfo("14 de agosto", "1h 30min", "Llegada", "LON", "Heathrow, T5", "09:30h")

    val fromLondonMadrid = TravelInfo("17 de agosto", "3h 25min", "Origen", "LON", "Heathrow, T5", "18:45h")
    val toMadridLondon = TravelInfo("17 de agosto", "3h 25min", "Llegada", "MAD", "Barajas, T4S", "22:10h")

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Outlined.ArrowBackIosNew, contentDescription = "backPlace", Modifier.size(16.dp), tint = Color(0xff142D55))
                    }
                },
                title = { Text(text = "") },
                actions = {
                    IconButton(onClick = { context.startActivity(infoIntent) }) {
                        Icon(imageVector = Icons.Outlined.Info, contentDescription = "info", Modifier.size(24.dp), tint = Color(0xff142D55))
                    }
                    IconButton(onClick = {
                        context.startActivity(mapsIntent)
                    }) {
                        Icon(imageVector = Icons.Outlined.Directions, contentDescription = "maps", Modifier.size(24.dp), tint = Color(0xff142D55))
                    }
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(8.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            PlaceImage(place = place, navController)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = place.name,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = MyTypography.fontFamily,
                color = Color(0xff142D55),
                modifier = Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(8.dp)) {
                if (place.schedule != null && place.price != null) {
                    PlaceSchedulePrice(place)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Text(
                    text = place.shortDesc,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MyTypography.fontFamily,
                    color = Color(0xff142D55)
                )
                MyDivider()
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = place.desc, fontFamily = MyTypography.fontFamily)
                if(place.name.contains("Aeropuerto")) {
                    when (place.desc) {
                        "1 de julio - Iberia" -> {
                            TravelCard(from = fromMadridBerlin, to = toBerlinMadrid)
                        }
                        "7 de julio - Lufthansa" -> {
                            TravelCard(from = fromBerlinMunich, to = toMunichBerlin)
                        }
                        "11 de julio - Lufthansa" -> {
                            TravelCard(from = fromMunichMadrid, to = toMadridMunich)
                        }
                        "14 de agosto - Iberia" -> {
                            TravelCard(from = fromMadridLondon, to = toLondonMadrid)
                        }
                        "17 de agosto - Iberia" -> {
                            TravelCard(from = fromLondonMadrid, to = toMadridLondon)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun PlaceImage(place: Place, navController: NavController) {
    IconButton(onClick = { navController.popBackStack() }) {
        Icon(imageVector = Icons.Outlined.ArrowBackIosNew, contentDescription = "back", Modifier.size(16.dp))
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff0856CF).copy(alpha = 0.2f),
            contentColor = Color.Black,
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        if (place.imgMe != null) {
            val context = LocalContext.current
            LoadImageFromAssets(context, place.imgMe)
        }
        else {
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(place.img)
                .crossfade(true)
                .build(),
                contentDescription = "background",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop)
        }
    }
}

@Composable
fun LoadImageFromAssets(context: Context, assetPath: String) {
    // Lee el archivo desde assets y conviértelo a Bitmap
    val bitmap: Bitmap? = loadBitmapFromAssets(context, assetPath)

    // Si el bitmap es nulo, puedes mostrar una imagen por defecto
    if (bitmap != null) {
        Image(bitmap = bitmap.asImageBitmap(), contentDescription = null)
    } else {
        // Imagen por defecto si no se encuentra la imagen
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = null, modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
            contentScale = ContentScale.Crop)
    }
}

// Función para cargar el Bitmap desde assets
fun loadBitmapFromAssets(context: Context, assetPath: String): Bitmap? {
    return try {
        val inputStream: InputStream = context.assets.open(assetPath)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Composable
fun PlaceSchedulePrice(place: Place) {
    Card(colors = CardDefaults.cardColors(
        containerColor = Color(0xff0856CF).copy(alpha = 0.2f),
        contentColor = Color(0xff142D55)
    )) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
            Text(place.price!!, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xff142D55), fontFamily = MyTypography.fontFamily)
            Spacer(modifier = Modifier.width(16.dp))
            Text(place.schedule!!, fontFamily = MyTypography.fontFamily)
        }
    }
    
}

@Composable
fun MyDivider() {
    Box(
        Modifier
            .height(2.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xff4B92FF), Color(0xff4B92FF).copy(0.01f))
                )
            )
            .fillMaxWidth()
    )
}

@Composable
fun TravelCard(from: TravelInfo, to: TravelInfo) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
            Icon(imageVector = Icons.Outlined.AirplanemodeActive, contentDescription = "airplane", modifier = Modifier.rotate(180f), tint = Color(0xff4B92FF))
            Icon(imageVector = Icons.Filled.Circle, contentDescription = "point", tint = Color(0xff4B92FF), modifier = Modifier.size(8.dp))
            Icon(imageVector = Icons.Filled.Circle, contentDescription = "point", tint = Color(0xff4B92FF), modifier = Modifier.size(8.dp))
            Icon(imageVector = Icons.Filled.Circle, contentDescription = "point", tint = Color(0xff4B92FF), modifier = Modifier.size(8.dp))
            Icon(imageVector = Icons.Filled.Circle, contentDescription = "point", tint = Color(0xff4B92FF), modifier = Modifier.size(8.dp))
            Text(text = to.duration, color = Color(0xff4B92FF), fontFamily = MyTypography.fontFamily)
            Icon(imageVector = Icons.Filled.Circle, contentDescription = "point", tint = Color(0xff0856CF).copy(alpha = 0.2f), modifier = Modifier.size(8.dp))
            Icon(imageVector = Icons.Filled.Circle, contentDescription = "point", tint = Color(0xff0856CF).copy(alpha = 0.2f), modifier = Modifier.size(8.dp))
            Icon(imageVector = Icons.Filled.Circle, contentDescription = "point", tint = Color(0xff0856CF).copy(alpha = 0.2f), modifier = Modifier.size(8.dp))
            Icon(imageVector = Icons.Filled.Circle, contentDescription = "point", tint = Color(0xff0856CF).copy(alpha = 0.2f), modifier = Modifier.size(8.dp))
            Icon(imageVector = Icons.Outlined.Place, contentDescription = "place", modifier = Modifier.rotate(180f), tint = Color(0xff0856CF).copy(alpha = 0.2f))
        }
        Column(
            Modifier
                .fillMaxWidth().padding(top = 2.dp)
                .weight(2f),
                horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(from.flight, fontFamily = MyTypography.fontFamily)
                Text(from.airport, fontWeight = FontWeight.Bold, color = Color(0xff142D55), fontFamily = MyTypography.fontFamily)
            }
            Spacer(modifier = Modifier.height(40.dp))
            Column {
                Text(to.flight, fontFamily = MyTypography.fontFamily)
                Text(to.airport, fontWeight = FontWeight.Bold, color = Color(0xff142D55), fontFamily = MyTypography.fontFamily)
            }
        }
        Column(
            Modifier
                .fillMaxWidth().padding(top = 4.dp)
                .weight(1f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
            Column {
                Text(from.country, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xff142D55), fontFamily = MyTypography.fontFamily)
                Text(from.time, fontFamily = MyTypography.fontFamily)
            }
            Icon(imageVector = Icons.AutoMirrored.Outlined.CompareArrows, contentDescription = "arrows", modifier = Modifier.rotate(90f).size(32.dp), tint = Color(0xff4B92FF))
            Column {
                Text(to.time, fontFamily = MyTypography.fontFamily)
                Text(to.country, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xff142D55), fontFamily = MyTypography.fontFamily)
            }
        }
    }
}