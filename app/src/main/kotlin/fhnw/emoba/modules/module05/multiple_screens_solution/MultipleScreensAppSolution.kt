package fhnw.emoba.modules.module05.multiple_screens_solution

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module05.multiple_screens_solution.ui.MultipleScreensUI
import fhnw.emoba.modules.module05.multiple_screens_solution.viewmodel.MultipleScreensModel

object MultipleScreensAppSolution : AppBase<MultipleScreensModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): MultipleScreensModel = viewModel<MultipleScreensModel>()

    @Composable
    override fun AppUi(viewModel: MultipleScreensModel) {
        MultipleScreensUI(viewModel)
    }
}