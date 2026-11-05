package fhnw.emoba.modules.module04.beers.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import fhnw.emoba.modules.module04.beers.data.Beer
import fhnw.emoba.modules.module04.beers.data.Repository
import fhnw.emoba.modules.module04.beers.viewmodel.TabId


class BeersAppModel : ViewModel() {
    val title = "There is more than Lager"

    //Todo: Welche Properties werden benoetigt - DONE
    // - damit zwischen Dark-Theme und Light-Theme gewechselt werden kann, default soll dark sein
    // - fuer die Daten (in diesem Fall Beers)
    // - fuer eine Tab Navigation (Funktionalität), welche die Beers nach beercolor filtert

    // Properties Theme
    var darkTheme by mutableStateOf(false)

    fun toggleTheme(){
        darkTheme = !darkTheme
    }

    // Properties Daten
    lateinit var beers: List<Beer>
    fun loadBeers(){
        beers = Repository.beers
    }

    // Properties Tab Navigation
    var selectedTab by mutableStateOf(TabId.ALL)

    val filteredBeers: List<Beer>
        get() = if (selectedTab == TabId.ALL) {
            beers
        } else {
            beers.filter { it.beercolor == selectedTab.title }
        }


    //Todo: wie können initial Daten geladen werden? - DONE
    init {
        loadBeers()
    }
}