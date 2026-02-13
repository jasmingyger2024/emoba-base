package fhnw.emoba.modules.module08.startrek

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module08.startrek.ui.StarTrekUI
import fhnw.emoba.modules.module08.startrek.viewmodel.StarTrekModel

object StarTrekApp : AppBase<StarTrekModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): StarTrekModel {
        val viewModel = viewModel<StarTrekModel>()
        viewModel.initializeSoundplayer(activity)
        return viewModel
    }

    @Composable
    override fun AppUi(viewModel: StarTrekModel) {
        StarTrekUI(viewModel)
    }

}