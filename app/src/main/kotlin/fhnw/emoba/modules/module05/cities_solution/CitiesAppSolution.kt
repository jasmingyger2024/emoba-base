package fhnw.emoba.modules.module05.cities_solution

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module05.cities_solution.data.CityRepository
import fhnw.emoba.modules.module05.cities_solution.ui.CitiesUI
import fhnw.emoba.modules.module05.cities_solution.viewmodel.CitiesModel

object CitiesAppSolution : AppBase<CitiesModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): CitiesModel {
        val viewModel = viewModel<CitiesModel>()
        CityRepository.loadCities(activity)
        viewModel.setCitiesRepository(CityRepository)
        return viewModel
    }

    @Composable
    override fun AppUi(viewModel: CitiesModel) {
        CitiesUI(viewModel)
    }
}