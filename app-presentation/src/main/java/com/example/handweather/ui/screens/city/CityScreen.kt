package com.example.handweather.ui.screens.city

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.City
import com.example.domain.model.currentweather.CurrentWeather
import com.example.handweather.R
import com.example.handweather.ui.screens.city.state.FetchingErrorState
import com.example.handweather.util.extensions.getCityBackground
import com.example.handweather.util.extensions.getWeatherIcon
import kotlin.concurrent.timer

@Composable
fun CityScreen(
    currentCity: City?,
    viewModel: CityScreenViewModel,
    onCloseScreenClicked: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    val currentCityWeather = state.cityCurrentWeather
    val contentLoading = state.contentLoadState
    val fetchingErrorState = state.fetchingError

    LaunchedEffect(Unit) {
        timer(
            initialDelay = 1000*2,
            period = 1000*15
        ) {
            viewModel.fetchCityWeather(currentCity)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(600.dp)
            .fillMaxWidth()
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            if (contentLoading) {
                Row {
                    Text(text = "Loading...")
                    CircularProgressIndicator(
                        Modifier.size(10.dp)
                    )
                }
            } else {
                CityScreenContent(
                    city = currentCity,
                    weather = currentCityWeather,
                    fetchingErrorState = fetchingErrorState
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 15.dp, end = 15.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close city screen icon",
                    modifier = Modifier.clickable {
                        onCloseScreenClicked()
                    }
                )
            }

        }

    }

}

@Composable
fun CityScreenContent(
    city: City?,
    weather: CurrentWeather?,
    fetchingErrorState: FetchingErrorState?
) {
    Box(
        Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(city?.getCityBackground() ?: R.drawable.no_city),
            alpha = 0.5f,
            contentDescription = "Generic city background image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .size(100.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colors.surface.copy(alpha = 0.5f))
                    .padding(5.dp)
            ) {
                Text(
                    text = "${city?.name ?: "-"}, ",
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.primary.copy(alpha = 0.8f),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = city?.countryFullName ?: "-",
                    color = MaterialTheme.colors.primary.copy(alpha = 0.8f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Thin
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(200.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = weather?.getWeatherIcon() ?: R.drawable.clear_sky
                    ),
                    alpha = 0.6f,
                    contentDescription = "Current weather icon",
                    modifier = Modifier.size(175.dp)
                )
                Text(
                    text = "${String.format("%.1f", weather?.temperature)}°c",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary.copy(alpha = 0.8f),
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color.Transparent)
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colors.surface.copy(alpha = 0.5f))
                    .padding(5.dp)
            ) {
                Text(
                    text = weather?.description ?: "-",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary.copy(alpha = 0.8f),
                    fontFamily = FontFamily.Monospace
                )
            }


            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colors.surface.copy(alpha = 0.5f))
                    .padding(5.dp)
            ) {

                Text(
                    text = "Max:${String.format("%.1f", weather?.maxTemp)}°c",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Min:${String.format("%.1f", weather?.minTemp)}°c",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )

            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colors.surface.copy(alpha = 0.5f))
                    .padding(5.dp)
            ) {
                Text(
                    text = "Feels like: ${String.format("%.1f", weather?.feelsLike)}°c",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary.copy(alpha = 0.8f),
                    fontFamily = FontFamily.Monospace
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                WeatherDetailIconCardCity(
                    value = "${String.format("%.1f", weather?.windSpeed)}Km/h",
                    iconId = R.drawable.windspeed
                )
                WeatherDetailIconCardCity(
                    value = "${weather?.humidity.toString()}%",
                    iconId = R.drawable.humidity
                )
                WeatherDetailIconCardCity(
                    value = "${(weather?.visibility?.div(10)).toString()}m",
                    iconId = R.drawable.visibility
                )

            }
        }

        AnimatedVisibility(
            visible = fetchingErrorState != null,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Color.Red.copy(alpha = 0.5f))
            ) {

                Text(
                    text = "Error fetching city weather: ${fetchingErrorState?.message ?: "Unknown"}",
                    color = MaterialTheme.colors.primary,
                    textAlign = TextAlign.Center
                )

            }
        }

    }
}

@Composable
fun WeatherDetailIconCardCity(value: String, iconId: Int) {
    Card(
        shape = RoundedCornerShape(30.dp),
        backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.7f),
        modifier = Modifier
            .padding(5.dp)
            .sizeIn(minWidth = 100.dp, minHeight = 30.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(5.dp)
        ) {
            Icon(
                painter = painterResource(id = iconId),
                tint = MaterialTheme.colors.primary.copy(alpha = 0.4f),
                contentDescription = "Weather parameter icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = value,
                color = MaterialTheme.colors.primary.copy(alpha = 0.4f),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}
