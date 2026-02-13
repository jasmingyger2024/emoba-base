package fhnw.emoba.modules.module06.weather_solution.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fhnw.emoba.modules.module06.weather_solution.data.City
import fhnw.emoba.modules.module06.weather_solution.data.CityRepository
import fhnw.emoba.modules.module06.weather_solution.data.Forecast
import fhnw.emoba.modules.module06.weather_solution.data.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Collections

class WeatherModel : ViewModel(){

    private val weatherIconCache =
        Collections.synchronizedMap(LRUCache<String, ImageBitmap>(20))

    val title = "WeatherApp"
    var isDarkTheme by mutableStateOf(true)

    lateinit var citiesRepo: CityRepository
    lateinit var weatherService: WeatherService

    var currentScreenId by mutableStateOf(ScreenId.WEATHER)

    var searchtext by mutableStateOf("")

    var countryCH by mutableStateOf(true)
        private set

    fun toggleTheme() {
        isDarkTheme = !isDarkTheme
    }

    fun toggleCountryCH() {
        countryCH = !countryCH
        launchSearch()
    }

    var countryDE by mutableStateOf(false)
        private set

    fun toggleCountryDE() {
        countryDE = !countryDE
        launchSearch()
    }

    //results from searching the repo:
    var currentCityList: List<City> by mutableStateOf(emptyList())

    var favoriteCities: List<City> by mutableStateOf(emptyList())


    fun loadSomeDefaultCities() {
            favoriteCities = listOf(
                citiesRepo.getCity(2661374), //Brugg
                citiesRepo.getCity(2925177), //Freiburg
                citiesRepo.getCity(2950159), //Berlin
                citiesRepo.getCity(2911298), //Hamburg
                citiesRepo.getCity(5128581), //New York City
                citiesRepo.getCity(2147714), //Sidney
                citiesRepo.getCity(8013441)  //Ponta Delgada (Azoren)
            )
    }

    var isLoading by mutableStateOf(false)
    var forecasts: List<Forecast> by mutableStateOf(emptyList())

    fun getForecastsOfFavoritesAsync(): Job {
        if (favoriteCities.isEmpty()){
            loadSomeDefaultCities()  //for demo purpose
        }
        isLoading = true
        forecasts = emptyList()
        val job = viewModelScope.launch(Dispatchers.IO) {
            forecasts = favoriteCities.mapNotNull { weatherService.requestForecast(it) }
            isLoading = false

            forecasts.forEach { loadWeatherIconsAsync(it) }
        }
        return job // for tests only
    }

    fun launchSearch() {
        var countryCodeFilter = ""
        if (countryCH) {
            countryCodeFilter += "CH,"
        }
        if (countryDE) {
            countryCodeFilter += "DE"
        }

        currentCityList = citiesRepo.search(searchtext, countryCodeFilter)
    }

    fun addFavCity(favCity: City) {
        favoriteCities = favoriteCities.plus(favCity)
        //load new weather data for favCities
        getForecastsOfFavoritesAsync()
        searchtext = ""
    }

    fun removeFavCity(favCity: City) {
        favoriteCities = favoriteCities.minus(favCity)
        //load new weather data for favCities
        getForecastsOfFavoritesAsync()
    }

    private fun loadWeatherIconsAsync(forecast: Forecast) {
        viewModelScope.launch(Dispatchers.IO){
            forecast.current.weatherImage = getWeatherIcon(forecast.current.weatherIcon)
        }
        forecast.nextHours.forEach {
            viewModelScope.launch(Dispatchers.IO) {
                it.weatherImage = getWeatherIcon(it.weatherIcon)
            }
        }
    }

    private fun getWeatherIcon(icon: String): ImageBitmap {
        if (weatherIconCache[icon] == null) {
            weatherIconCache[icon] = weatherService?.requestWeatherIcon(icon)?.asImageBitmap()
        }
        return weatherIconCache[icon]!!
    }
}