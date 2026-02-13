package fhnw.emoba.modules.module05.squad

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module05.squad.ui.SquadUI
import fhnw.emoba.modules.module05.squad.viewmodel.SquadModel

object SquadApp : AppBase<SquadModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): SquadModel {
        val viewModel = viewModel<SquadModel>()
        viewModel.loadRepoSquad(activity)
        return viewModel
    }
    
    @Composable
    override fun AppUi(viewModel: SquadModel) {
        SquadUI(viewModel)
    }
}