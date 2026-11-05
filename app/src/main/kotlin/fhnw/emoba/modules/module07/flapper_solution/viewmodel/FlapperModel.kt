package fhnw.emoba.modules.module07.flapper_solution.viewmodel

import android.content.ClipboardManager
import android.content.Context
import android.media.MediaPlayer
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import fhnw.emoba.R
import fhnw.emoba.modules.module07.flapper_solution.data.Flap
import fhnw.emoba.modules.module07.flapper_solution.data.MqttConnector

class FlapperModel : ViewModel() {
    val title      = "FlapperApp"
    val mqttBroker = "v001495.fhnw.ch"
    val mainTopic  = "fhnw/emoba/flapperapp"
    val me         = "Geissen Peter"

    val allFlaps = mutableStateListOf<Flap>()

    var notificationMessage by mutableStateOf("")
    var flapsPublished      by mutableIntStateOf(0)
    var message             by mutableStateOf("Hello")

    private val mqttConnector by lazy { MqttConnector(mqttBroker) }

    lateinit var soundPlayer: MediaPlayer

    lateinit var clipboardManager: ClipboardManager

    fun initializeNewActivity(activity: ComponentActivity){
        clipboardManager = getClipBoardManager(activity)
        soundPlayer = MediaPlayer.create(activity, R.raw.what_302)
        connectAndSubscribe()
    }

    private fun connectAndSubscribe(){
        mqttConnector.connectAndSubscribe(
            topic        = mainTopic,
            onNewMessage = { allFlaps.add(Flap(it))
                             playSound()
                           },
            onError      = {_, p ->
                               notificationMessage = p
                               playSound()
                           }
        )
    }
    
    fun publish(){
        mqttConnector.publish(
            topic       = mainTopic,
            message     = Flap(sender  = me,
                               message = message),
            onPublished = { flapsPublished++ })
    }

    private fun playSound(){
        soundPlayer.seekTo(0)
        soundPlayer.start()
    }

    private fun getClipBoardManager(activity: ComponentActivity)  =
        activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

}