package fhnw.emoba.modules.module03.material.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MaterialModel : ViewModel() {
    // der Applikationsname bleibt unveraendert, also 'val'
    val title = "Material Design Example"
    
    // die Aenderung des Themes muss ein Recompose ausloesen, also 'mutableStateOf'
    var darkTheme by mutableStateOf(false)

    var typoBig by mutableStateOf(false)
    private set
    
    fun toggleTheme(){
        darkTheme = !darkTheme
    }

    fun toggleFontSize(){
        typoBig = !typoBig
    }
}