package fhnw.emoba.modules.module09.gps

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module09.gps.data.GPSConnector
import fhnw.emoba.modules.module09.gps.ui.GpsUI
import fhnw.emoba.modules.module09.gps.viewmodel.GpsModel

object GpsApp : AppBase<GpsModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): GpsModel {
        val viewModel = viewModel<GpsModel>()
        viewModel.updateLocator(GPSConnector(activity))
        viewModel.updateUriHandler(activity)
        return viewModel
    }


    @Composable
    override fun AppUi(viewModel: GpsModel) {
        GpsUI(viewModel)
    }
}