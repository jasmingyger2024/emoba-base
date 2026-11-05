package fhnw.emoba.modules.module07.flapper_solution

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module07.flapper_solution.ui.FlapperUI
import fhnw.emoba.modules.module07.flapper_solution.viewmodel.FlapperModel

object FlapperAppSolution : AppBase<FlapperModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): FlapperModel {
        val viewModel = viewModel<FlapperModel>()
        viewModel.initializeNewActivity(activity)
        return viewModel
    }

    @Composable
    override fun AppUi(viewModel: FlapperModel) {
        FlapperUI(viewModel)
    }

}