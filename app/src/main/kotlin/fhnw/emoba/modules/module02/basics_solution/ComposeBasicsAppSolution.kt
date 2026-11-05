package fhnw.emoba.modules.module02.basics_solution

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module02.basics_solution.ui.BasicsUI
import fhnw.emoba.modules.module02.basics_solution.viewmodel.BasicsModel

object ComposeBasicsAppSolution : AppBase<BasicsModel>() {

	@Composable
	override fun createViewModel(activity: ComponentActivity): BasicsModel = viewModel<BasicsModel>()
    
    @Composable
    override fun AppUi(viewModel: BasicsModel) {
        BasicsUI(viewModel)
    }
    
}