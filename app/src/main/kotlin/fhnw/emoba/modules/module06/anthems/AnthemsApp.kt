package fhnw.emoba.modules.module06.anthems

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module06.anthems.ui.AnthemsUI
import fhnw.emoba.modules.module06.anthems.viewmodel.AnthemsModel

object AnthemsApp : AppBase<AnthemsModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): AnthemsModel = viewModel<AnthemsModel>()

    @Composable
    override fun AppUi(viewModel: AnthemsModel) {
        AnthemsUI(viewModel)
    }
}