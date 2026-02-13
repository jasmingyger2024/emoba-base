package fhnw.emoba.modules.module10.canvasplayground

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module10.canvasplayground.ui.CanvasPane
import fhnw.emoba.modules.module10.canvasplayground.viewmodel.CanvasPlaygroundViewModel

object CanvasPlaygroundApp : AppBase<ViewModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): ViewModel = viewModel<CanvasPlaygroundViewModel>()

    @Composable
    override fun AppUi(viewModel: ViewModel) {
        CanvasPane()
    }
}