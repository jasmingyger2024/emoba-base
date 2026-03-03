package fhnw.emoba.modules.module02.mvvm

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module02.mvvm.ui.MVVMUi
import fhnw.emoba.modules.module02.mvvm.viewmodel.MVVMModel

object MVVMApp: AppBase<MVVMModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): MVVMModel = viewModel<MVVMModel>()

    @Composable
    override fun AppUi(viewModel: MVVMModel) {
        MVVMUi(viewModel) //Todo: die View braucht ein ViewModel
    }
}