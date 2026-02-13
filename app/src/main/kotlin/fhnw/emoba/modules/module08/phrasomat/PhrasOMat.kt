package fhnw.emoba.modules.module08.phrasomat

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module08.phrasomat.ui.PhrasOMatUI
import fhnw.emoba.modules.module08.phrasomat.viewmodel.PhrasOMatModel

object PhrasOMat : AppBase<PhrasOMatModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): PhrasOMatModel = viewModel<PhrasOMatModel>()

    @Composable
    override fun AppUi(viewModel: PhrasOMatModel) {
        PhrasOMatUI(viewModel)
    }

}
