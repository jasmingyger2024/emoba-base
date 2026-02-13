package fhnw.emoba.modules.module07.mqtt.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import fhnw.emoba.modules.module07.mqtt.data.MqttConnector

class MqttModel : ViewModel() {
    val title      = "MqttApp"
    val mqttBroker = "v001495.fhnw.ch"
    val topic      = "fhnw/emoba/mqttplayground"

    private val mqttConnector by lazy { MqttConnector(mqttBroker) }

    var messagesReceived  by mutableIntStateOf(0)
    var messagesPublished by mutableIntStateOf(0)

    init {
        connectAndSubscribe()
    }

    private fun connectAndSubscribe(){
        mqttConnector.connectAndSubscribe(topic        = topic,
                                          onNewMessage = { messagesReceived++ })
    }
    
    fun publish(){
        mqttConnector.publish(topic       = topic,
                              message     = "hello",
                              onPublished = { messagesPublished++ })
    }
    
}