package fhnw.emoba.modules.module05.cities

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module05.cities.ui.CitiesUI
import fhnw.emoba.modules.module05.cities.viewmodel.CitiesModel

object CitiesApp : AppBase<CitiesModel>() {
    @Composable
    override fun createViewModel(activity: ComponentActivity): CitiesModel = viewModel<CitiesModel>()


    @Composable
    override fun AppUi(viewModel: CitiesModel) {
        CitiesUI(viewModel)

    }
}