package fhnw.emoba.modules.module03.flags

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase

object FunWithFlagsApp: AppBase<FunWithFlagsViewModel>(){
    @Composable
    override fun createViewModel(activity: ComponentActivity): FunWithFlagsViewModel {
        return viewModel()
    }

    @Composable
    override fun AppUi(viewModel: FunWithFlagsViewModel) {
        FunWithFlagsUI(viewModel)
    }
}