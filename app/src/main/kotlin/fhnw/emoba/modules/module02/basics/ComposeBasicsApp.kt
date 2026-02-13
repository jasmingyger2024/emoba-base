package fhnw.emoba.modules.module02.basics

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module02.basics.ui.BasicsUI
import fhnw.emoba.modules.module02.basics.viewmodel.BasicsModel

object ComposeBasicsApp : AppBase<BasicsModel>() {

	@Composable
	override fun createViewModel(activity: ComponentActivity): BasicsModel = viewModel<BasicsModel>()

    @Composable
    override fun AppUi(viewModel: BasicsModel) {
        BasicsUI(viewModel)
    }
    
}