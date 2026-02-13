package fhnw.emoba.modules.module07.mqtt

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module07.mqtt.ui.MqttUI
import fhnw.emoba.modules.module07.mqtt.viewmodel.MqttModel

object MqttApp : AppBase<MqttModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): MqttModel = viewModel<MqttModel>()

    @Composable
    override fun AppUi(viewModel: MqttModel) {
        MqttUI(viewModel)
    }
}