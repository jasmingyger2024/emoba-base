package fhnw.emoba.modules.module04.beers

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module04.beers.ui.BeersAppUI
import fhnw.emoba.modules.module04.beers.viewmodel.BeersAppModel

object BeersApp : AppBase<BeersAppModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): BeersAppModel = viewModel<BeersAppModel>()

    @Composable
    override fun AppUi(viewModel: BeersAppModel) {
        BeersAppUI(viewModel)
    }
}