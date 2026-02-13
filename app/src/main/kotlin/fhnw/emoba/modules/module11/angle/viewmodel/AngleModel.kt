package fhnw.emoba.modules.module11.angle.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AngleModel : ViewModel(){
    var angle by mutableFloatStateOf(73f)
}