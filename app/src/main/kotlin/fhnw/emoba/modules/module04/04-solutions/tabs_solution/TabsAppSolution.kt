package fhnw.emoba.modules.module04.tabs_solution

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module04.tabs_solution.ui.TabsUI
import fhnw.emoba.modules.module04.tabs_solution.viewmodel.TabsModel

object TabsAppSolution : AppBase<TabsModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): TabsModel = viewModel<TabsModel>()

    @Composable
    override fun AppUi(viewModel: TabsModel) {
        TabsUI(viewModel)
    }
}