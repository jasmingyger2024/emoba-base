package fhnw.emoba.modules.module07.flapper.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import fhnw.emoba.modules.module07.mqtt.data.MqttConnector

class FlapperModel : ViewModel() {
    val title      = "FlapperApp"
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
            onNewMessage = { messagesReceived++ }) // definiert was passiert wenn ein neues Message ankommt
    }

    fun publish(){
        mqttConnector.publish(topic       = topic, // das topic ist für die App dann nicht immer gleich sondern für jede person anders
            message     = "hello",
            onPublished = { messagesPublished++ }) // definiert was passiert wenn ein neues Message ankommt

    }

}