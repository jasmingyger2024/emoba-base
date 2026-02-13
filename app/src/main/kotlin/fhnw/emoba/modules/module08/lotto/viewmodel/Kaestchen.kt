package fhnw.emoba.modules.module08.lotto.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import fhnw.emoba.modules.module08.lotto.data.Ziehung

class Kaestchen (val tip: Ziehung) : ViewModel() {
    var richtige by mutableIntStateOf(0)
    private set   //darf nur ueber 'ergebnis' veraendert werden, nicht direkt

    var superzahlRichtig by mutableStateOf(false)
    private set

    fun ergebnis(ziehung: Ziehung){
        richtige         = ziehung.gewinnZahlen.count { tip.gewinnZahlen.contains(it) }
        superzahlRichtig = ziehung.superzahl == tip.superzahl
    }
}