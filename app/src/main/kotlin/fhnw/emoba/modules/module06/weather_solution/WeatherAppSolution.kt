package fhnw.emoba.modules.module06.weather_solution

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module06.weather_solution.data.CityRepository
import fhnw.emoba.modules.module06.weather_solution.data.impl.LocalWeatherService
import fhnw.emoba.modules.module06.weather_solution.ui.WeatherUI
import fhnw.emoba.modules.module06.weather_solution.viewmodel.WeatherModel

object WeatherAppSolution : AppBase<WeatherModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): WeatherModel {
        val viewModel = viewModel<WeatherModel>()
        val citiesRepo = CityRepository(activity)
        viewModel.citiesRepo = citiesRepo
        //val weatherService = RemoteWeatherService()       // API call real weather
        val weatherService = LocalWeatherService(activity) // Local means: same Brugg weather from local json file for all cities
        viewModel.weatherService = weatherService
        viewModel.getForecastsOfFavoritesAsync()
        return viewModel
    }

    @Composable
    override fun AppUi(viewModel: WeatherModel) {
        WeatherUI(viewModel)
    }
}