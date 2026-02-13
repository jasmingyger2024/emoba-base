package fhnw.emoba.modules.module10.led.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class LedModel : ViewModel() {
    private val colors = listOf(Color.Red, Color.Green, Color.Blue)

    var on    by mutableStateOf(true)
    var color by mutableStateOf(Color.Red)
    private set

    fun changeColor(){
        var idx = colors.indexOf(color) + 1
        if(idx >= colors.size){
            idx = 0
        }

        color = colors[idx]
    }
}