package fhnw.emoba.modules.module02.mvvm.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MVVMModel : ViewModel() {
    // darzustellende Information
    var title by mutableStateOf("View meets Model")
    // var counter = mutableStateOf(0) // option 1
    var counter by mutableStateOf(0) // damit hat man direkt den Wert als INT. by muss importiert werden!


    fun increase() {
        //counter.value++ // option 1: ounter ist vom Typ state, den wert davon kann man mit .value aufrufen
        counter++ // möglich wenn oben by verwendet wird
    }

    fun reset() {
        counter = 0
    }
}