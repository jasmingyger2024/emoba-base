package fhnw.emoba.modules.module04.map_solution

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module04.map_solution.ui.MapUI
import fhnw.emoba.modules.module04.map_solution.viewmodel.MapModel

object MapAppSolution : AppBase<MapModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): MapModel = viewModel<MapModel>()

    @Composable
    override fun AppUi(viewModel: MapModel) {
        MapUI(viewModel)
    }
}