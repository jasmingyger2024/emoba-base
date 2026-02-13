package fhnw.emoba.modules.module04.map

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module04.map.ui.MapUI
import fhnw.emoba.modules.module04.map.viewmodel.MapModel

object MapApp : AppBase<MapModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): MapModel = viewModel<MapModel>()

    @Composable
    override fun AppUi(viewModel: MapModel) {
        MapUI(viewModel)
    }
}