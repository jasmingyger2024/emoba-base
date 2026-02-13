package fhnw.emoba.modules.module11.angle

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module11.angle.ui.AngleUI
import fhnw.emoba.modules.module11.angle.viewmodel.AngleModel

object AngleApp : AppBase<AngleModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): AngleModel = viewModel<AngleModel>()

    @Composable
    override fun AppUi(viewModel: AngleModel) {
        AngleUI(viewModel)
    }
}