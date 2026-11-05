package fhnw.emoba.modules.module04.countries_solution

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module04.countries_solution.ui.CountriesUI
import fhnw.emoba.modules.module04.countries_solution.viewmodel.CountriesModel

object CountriesAppSolution : AppBase<CountriesModel>() {
    
    @Composable
    override fun createViewModel(activity: ComponentActivity): CountriesModel = viewModel<CountriesModel>()
    
    @Composable
    override fun AppUi(viewModel: CountriesModel) {
        CountriesUI(viewModel)
    }
}