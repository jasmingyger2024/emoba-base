package fhnw.emoba.modules.module10.led

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module10.led.ui.LedAppUI
import fhnw.emoba.modules.module10.led.viewmodel.LedModel

object LedApp : AppBase<LedModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): LedModel = viewModel<LedModel>()

    @Composable
    override fun AppUi(viewModel: LedModel) {
        LedAppUI(viewModel)
    }
}