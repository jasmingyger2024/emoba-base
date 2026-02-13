package fhnw.emoba.modules.module09.uguu

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module09.uguu.ui.UguuUI
import fhnw.emoba.modules.module09.uguu.viewmodel.UguuModel

object UguuApp : AppBase<UguuModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): UguuModel {
        val viewModel = viewModel<UguuModel>()
        viewModel.initializeWithNewActivity(activity)
        return viewModel
    }

    @Composable
    override fun AppUi(viewModel: UguuModel) {
        UguuUI(viewModel)
    }
}