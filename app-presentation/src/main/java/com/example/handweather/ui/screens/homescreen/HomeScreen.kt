package com.example.handweather.ui.screens.homescreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.forecast.Forecast
import com.example.handweather.R
import com.example.handweather.locationservice.DeviceLocationManager
import com.example.handweather.ui.screens.homescreen.state.HomeScreenState
import com.example.handweather.util.extensions.getForeCastIcon
import com.example.handweather.util.extensions.getWeatherIcon
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = getViewModel(),
    onCityWeathersClicked: () -> Unit,
    locationManager: DeviceLocationManager
) {

    val state by viewModel.state.collectAsState()

    DisposableEffect(Unit) {
        locationManager.setOnLocationUpdateCallback {
            viewModel.fetchCurrentWeatherAndCurrentForecast()
        }
        onDispose {
            locationManager.clearLocationUpdateCallback()
        }
    }

    HomeScreenContent(
        state = state,
        onCityWeathersClicked = { onCityWeathersClicked() }
    )

}

@Composable
fun HomeScreenContent(state: HomeScreenState, onCityWeathersClicked: () -> Unit) {

    val weather = state.currentLocationWeather
    val forecasts = state.currentLocationForecasts

    val contentLoading = state.contentLoadState
    val forecastLoading = state.forecastLoadState

    Box(
        Modifier
            .fillMaxSize()
    ) {

        if (contentLoading) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = "Loading weather...",
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
                CircularProgressIndicator(
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.size(20.dp)
                )
            }
        } else {
            Image(
                painter = painterResource(id = R.drawable.no_city),
                alpha = 0.3f,
                contentDescription = "Generic city background image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .size(500.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .align(Alignment.TopCenter)
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Row {
                    Text(
                        text = weather?.cityName ?: "-"
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        imageVector = Icons.Rounded.LocationOn,
                        contentDescription = "Current location indicator icon"
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
                        alpha = 0.5f,
                        contentDescription = "Current weather icon",
                        modifier = Modifier.size(175.dp)
                    )
                    Text(
                        text = "${String.format("%.1f", weather?.temperature)}°c",
                        textAlign = TextAlign.Center,
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .background(Color.Transparent)
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = weather?.description ?: "-",
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Monospace
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Max:${String.format("%.1f", weather?.maxTemp)}°c",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Min:${String.format("%.1f", weather?.minTemp)}°c",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )

                }

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Feels like: ${String.format("%.1f", weather?.feelsLike)}°c",
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Monospace
                )


                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    WeatherDetailIconCard(
                        value = "${String.format("%.1f", weather?.windSpeed)}Km/h",
                        iconId = R.drawable.windspeed
                    )
                    WeatherDetailIconCard(
                        value = "${weather?.humidity.toString()}%",
                        iconId = R.drawable.humidity
                    )
                    WeatherDetailIconCard(
                        value = "${(weather?.visibility?.div(10)).toString()}m",
                        iconId = R.drawable.visibility
                    )

                }

                Spacer(modifier = Modifier.height(15.dp))

                ForecastView(
                    forecasts = forecasts,
                    isLoading = forecastLoading
                )

            }

            Image(
                painter = painterResource(id = R.drawable.world_icon),
                contentDescription = "City weathers button icon",
                modifier = Modifier
                    .size(70.dp)
                    .padding(end = 20.dp, bottom = 20.dp)
                    .align(Alignment.BottomEnd)
                    .clickable {
                        onCityWeathersClicked()
                    }
            )
        }


    }

}

@Composable
fun WeatherDetailIconCard(value: String, iconId: Int) {
    Card(
        shape = RoundedCornerShape(30.dp),
        contentColor = MaterialTheme.colors.surface.copy(alpha = 0.5f),
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ForecastView(forecasts: List<Forecast>?, isLoading: Boolean?) {
    if (isLoading == true) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Loading forecast...",
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
            )
            CircularProgressIndicator(
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.size(20.dp)
            )
        }
    } else {
        if (forecasts != null) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.Transparent)
            ) {
                Text(
                    text = "four days forecast:",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    contentPadding = PaddingValues(vertical = 10.dp, horizontal = 10.dp),
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                ) {

                    items(forecasts) {
                        ForecastCard(forecast = it)
                    }
                }
            }
        }
    }
}

@Composable
fun ForecastCard(forecast: Forecast) {
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = MaterialTheme.colors.surface.copy(alpha = 0.5f),
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 5.dp)
            .size(45.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = forecast.getForeCastIcon()),
                alpha = 0.7f,
                contentDescription = "Day of the week forecast icon",
                modifier = Modifier
                    .size(50.dp)
            )
            Text(
                text = "${String.format("%.1f", forecast.temp)}°c",
                color = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

        }
    }
}
