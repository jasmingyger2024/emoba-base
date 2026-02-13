package fhnw.emoba.modules.module04.tabs

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module04.tabs.ui.TabsUI
import fhnw.emoba.modules.module04.tabs.viewmodel.TabsModel

object TabsApp : AppBase<TabsModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): TabsModel = viewModel<TabsModel>()

    @Composable
    override fun AppUi(viewModel: TabsModel) {
        TabsUI(viewModel)
    }
}