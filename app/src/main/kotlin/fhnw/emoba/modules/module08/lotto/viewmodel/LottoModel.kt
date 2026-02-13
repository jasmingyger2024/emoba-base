package fhnw.emoba.modules.module08.lotto.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fhnw.emoba.modules.module08.lotto.data.Ziehung
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LottoModel : ViewModel() {
    val title = "Lotto 6 aus 49"

    //Todo: ersetzen durch geeignete Variante, um die Liste von allen Ziehungen zu verwalten
    val alleZiehungen = listOf<Ziehung>()

    //Todo: ersetzen durch geeignete Variante, um die Liste aller Kaestchen des aktuellen Lottoscheins zu verwalten
    var lottoschein = listOf<Kaestchen>()

    fun neuerLottoschein(anzahlKaestchen: Int){
        // auch wenn es diesem Beispiel kein Performanceproblem gibt:
        // "Datenservices" ruft man asynchron auf
        viewModelScope.launch(Dispatchers.IO) {
            //Todo: mittels 'gewinnzahlenZiehen()'( siehe 'Ziehungsgeraet') eine Liste
            //von Kaestchen erzeugen und 'lottoschein' zuweisen

        }
    }

    fun neueZiehung(){
        viewModelScope.launch(Dispatchers.IO) {
            //Todo: neue Gewinnzahlen ziehen (siehe 'Ziehungsgeraet.gewinnzahlenziehen')
            // und 'alleZiehungen' hinzufuegen


            //Todo: bei allen Kaestchen des aktuellen Lottoscheins das ergebnis setzen
        }
    }
}