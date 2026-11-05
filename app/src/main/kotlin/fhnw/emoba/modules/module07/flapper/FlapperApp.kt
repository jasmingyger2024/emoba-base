package fhnw.emoba.modules.module07.flapper

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module07.flapper.ui.FlapperUI
import fhnw.emoba.modules.module07.flapper.viewmodel.FlapperModel

object FlapperApp : AppBase<FlapperModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): FlapperModel = viewModel<FlapperModel>()

    @Composable
    override fun AppUi(viewModel: FlapperModel) {
        FlapperUI(viewModel)
    }
}
