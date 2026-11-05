package fhnw.emoba.modules.module04.beers_solution

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module04.beers_solution.ui.BeersAppUI
import fhnw.emoba.modules.module04.beers_solution.viewmodel.BeersAppModel

object BeersAppSolution : AppBase<BeersAppModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): BeersAppModel = viewModel<BeersAppModel>()

    @Composable
    override fun AppUi(viewModel: BeersAppModel) {
        BeersAppUI(viewModel)
    }
}