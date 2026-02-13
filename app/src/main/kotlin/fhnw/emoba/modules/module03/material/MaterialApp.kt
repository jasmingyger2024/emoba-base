package fhnw.emoba.modules.module03.material

import androidx.activity.ComponentActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module03.material.ui.MaterialUI
import fhnw.emoba.modules.module03.material.viewmodel.MaterialModel

object MaterialApp : AppBase<MaterialModel>() {
    
    @Composable
    override fun createViewModel(activity: ComponentActivity): MaterialModel = viewModel<MaterialModel>()
    
    @Composable
    override fun AppUi(viewModel: MaterialModel) {
        //on start use system setting for dark theme or not, can be changed inside app by user
        viewModel.darkTheme = isSystemInDarkTheme()
        MaterialUI(viewModel)
    }
}