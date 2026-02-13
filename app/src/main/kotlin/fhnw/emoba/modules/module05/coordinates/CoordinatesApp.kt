package fhnw.emoba.modules.module05.coordinates

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module05.coordinates.ui.CoordinatesUI
import fhnw.emoba.modules.module05.coordinates.viewmodel.CoordinatesModel

object CoordinatesApp : AppBase<CoordinatesModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): CoordinatesModel = viewModel<CoordinatesModel>()
    
    @Composable
    override fun AppUi(viewModel: CoordinatesModel) {
        CoordinatesUI(viewModel)
    }
}