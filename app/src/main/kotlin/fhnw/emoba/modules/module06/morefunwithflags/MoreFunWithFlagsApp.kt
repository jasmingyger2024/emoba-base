package fhnw.emoba.modules.module06.morefunwithflags

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module06.morefunwithflags.data.impl.LocalFlagService
import fhnw.emoba.modules.module06.morefunwithflags.ui.MoreFunWithFlagsUI
import fhnw.emoba.modules.module06.morefunwithflags.viewmodel.MoreFunWithFlagsModel

object MoreFunWithFlagsApp : AppBase<MoreFunWithFlagsModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): MoreFunWithFlagsModel {
        val viewModel = viewModel<MoreFunWithFlagsModel>()
        viewModel.flagService = LocalFlagService(activity)
        return viewModel
    }
    
    @Composable
    override fun AppUi(viewModel: MoreFunWithFlagsModel) {
        MoreFunWithFlagsUI(viewModel)
    }
}