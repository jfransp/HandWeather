package com.example.handweather.ui.screens.locations

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.domain.model.City
import com.example.handweather.ui.screens.city.CityScreen
import com.example.handweather.ui.screens.city.CityScreenViewModel
import com.example.handweather.ui.screens.locations.helper.handleLocationListScreenAction
import com.example.handweather.util.extensions.getCityIcon
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun LocationsListScreen(
    viewModel: LocationsListScreenViewModel = getViewModel(),
    onBackClicked: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    val cities = state.cities
    val selectedCity = state.selectedCity

    val bottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )
    val bottomSheetScope = rememberCoroutineScope()

    val cityScreenViewModel: CityScreenViewModel = getViewModel()

    LaunchedEffect(Unit) {
        viewModel.action.collect() { action ->
            handleLocationListScreenAction(
                action = action,
                sheetScope = bottomSheetScope,
                sheetState = bottomSheetState,
                cityScreenViewModel = cityScreenViewModel
            )
        }
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetElevation = 5.dp,
        sheetContent = {
            CityScreen(
                viewModel = cityScreenViewModel,
                currentCity = selectedCity,
                onCloseScreenClicked = {
                    bottomSheetScope.launch {
                        bottomSheetState.animateTo(ModalBottomSheetValue.Hidden)
                    }
                }
            )
        },
        sheetShape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
    ) {

        Box(Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                contentPadding = PaddingValues(vertical = 10.dp, horizontal = 10.dp),
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 10.dp)
                    .align(Alignment.Center)
            ) {

                items(items = cities) { city ->
                    CityCard(
                        city = city,
                        onCardClicked = { viewModel.onCityClicked(city) }
                    )
                }

            }

            Box(
                Modifier
                    .padding(top = 15.dp, start = 15.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back button icon",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(35.dp)
                        .clickable {
                            onBackClicked()
                        }
                )
            }

        }

    }

}

@Composable
fun CityCard(city: City, onCardClicked: (city: City) -> Unit) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(vertical = 15.dp, horizontal = 10.dp)
            .clickable {
                onCardClicked(city)
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp)
        ) {

            Text(
                text = city.name ?: "-",
                color = MaterialTheme.colors.primary.copy(alpha = 0.5f),
                fontWeight = FontWeight.Bold
            )

            Image(
                painter = painterResource(id = city.getCityIcon()),
                contentDescription = "City icon",
                modifier = Modifier
                    .size(65.dp)
                    .padding(5.dp)
                    .alpha(0.5f)
            )

        }
    }
}
