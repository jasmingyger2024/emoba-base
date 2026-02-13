package fhnw.emoba.modules.module10.goofieball

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module10.goofieball.ui.GoovieBallUI
import fhnw.emoba.modules.module10.goofieball.viewmodel.GoofieBallModel

object GoofieBall : AppBase<GoofieBallModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): GoofieBallModel {
        val viewModel = viewModel<GoofieBallModel>()
        viewModel.initializeSound(activity)
        return viewModel
    }

    @Composable
    override fun AppUi(viewModel: GoofieBallModel) {
        GoovieBallUI(viewModel)
    }
}