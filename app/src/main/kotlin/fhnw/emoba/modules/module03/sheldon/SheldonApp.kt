package fhnw.emoba.modules.module03.sheldon

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase

object SheldonApp: AppBase<SheldonViewModel>() {
    @Composable
    override fun createViewModel(activity: ComponentActivity): SheldonViewModel {
        return viewModel()
    }

    @Composable
    override fun AppUi(viewModel: SheldonViewModel) {
        SheldonUI(viewModel)
    }
}