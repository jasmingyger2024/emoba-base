package fhnw.emoba.modules.module06.weather_solution.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import fhnw.emoba.modules.module06.weather_solution.ui.screens.CitiesScreen
import fhnw.emoba.modules.module06.weather_solution.ui.screens.WeatherScreen
import fhnw.emoba.modules.module06.weather_solution.ui.theme.WeatherAppTheme
import fhnw.emoba.modules.module06.weather_solution.viewmodel.ScreenId
import fhnw.emoba.modules.module06.weather_solution.viewmodel.WeatherModel


@Composable
fun WeatherUI(model: WeatherModel) {
    with(model) {
        WeatherAppTheme(isDarkTheme) {
            Crossfade(targetState = currentScreenId) { screen ->
                when (screen) {
                    ScreenId.WEATHER -> {
                        WeatherScreen(
                            title = screen.title,
                            isDarkTheme = isDarkTheme,
                            isLoading = isLoading,
                            forecasts = forecasts,
                            toggleTheme = { isDarkTheme = !isDarkTheme },
                            removeFavorite = { city -> removeFavCity(city) },
                            navigateToCities = { currentScreenId = ScreenId.CITIES }
                        )
                    }
                    ScreenId.CITIES -> {
                        CitiesScreen(
                            title = screen.title,
                            isDarkTheme = isDarkTheme,
                            searchText = searchtext,
                            countryCH = countryCH,
                            countryDE = countryDE,
                            currentCityList = currentCityList,
                            totalCityCount = citiesRepo.data.size,
                            onSearchTextChanged = { searchtext = it },
                            launchSearch = { launchSearch() },
                            toggleCountryCH = { toggleCountryCH() },
                            toggleCountryDE = { toggleCountryDE() },
                            addToFavorite = { city -> addFavCity(city) },
                            navigateBack = { currentScreenId = ScreenId.WEATHER }
                        )
                    }
                }
            }
        }
    }
}