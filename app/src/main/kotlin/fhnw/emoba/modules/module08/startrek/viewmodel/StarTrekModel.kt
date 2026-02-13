package fhnw.emoba.modules.module08.startrek.viewmodel

import android.media.MediaPlayer
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fhnw.emoba.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// kann unveraendert bleiben

class StarTrekModel : ViewModel(){
    val title         = "Star-Trek App"
    val messageKirk   = "Beam us up, Scotty!"
    val reponseScotty = "Ay, Captain."
    private var beamjob = viewModelScope.launch {  }

    var transporterStatus by mutableFloatStateOf(0.0f)
        private set

    private lateinit var soundPlayer: MediaPlayer

    fun initializeSoundplayer(activity: ComponentActivity){
        if (!this::soundPlayer.isInitialized){
            soundPlayer = MediaPlayer.create(activity, R.raw.transporter)
        }
    }

    fun updateTransporterStatus(status: Float){
        if(status == transporterStatus){
            return
        }

        if(transporterStatus <= 0.1f && status > 0.1f ){
            playSound()
        }
        transporterStatus = status

        if(transporterStatus > 0.5f){
            continueBeamMeUp()
        } else {
            continueBeamMeDown()
        }
    }

    private fun continueBeamMeDown() {
        beamjob.cancel()
        beamjob = viewModelScope.launch {
            while (transporterStatus>0.01f){
                transporterStatus-=0.01f
                delay(200)
            }
        }
    }

    private fun continueBeamMeUp() {
        beamjob.cancel()
        beamjob = viewModelScope.launch {
            while (transporterStatus<0.99f){
                transporterStatus+=0.01f
                delay(200)
            }
        }
    }

    private fun playSound(){
        soundPlayer.seekTo(0)
        soundPlayer.start()
    }

}