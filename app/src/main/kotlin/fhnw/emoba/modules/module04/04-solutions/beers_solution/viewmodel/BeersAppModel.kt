package fhnw.emoba.modules.module04.beers_solution.viewmodel

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fhnw.emoba.modules.module04.beers_solution.data.Beer
import fhnw.emoba.modules.module04.beers_solution.data.Repository
import kotlinx.coroutines.launch


class BeersAppModel : ViewModel() {
    val title = "There is more than Lager"
    
    var beers by mutableStateOf(emptyList<Beer>())
    
    var darkTheme by mutableStateOf(true)
    private set

    var selectedTab by mutableStateOf(TabId.ALL)
    var lazyliststate by mutableStateOf(LazyListState())
    
    fun toggleTheme(){
        darkTheme = !darkTheme
    }

    init {
        loadData()
    }
    
    fun loadData() {
        viewModelScope.launch {
            beers = if (selectedTab.beercolor == "all") {
                Repository.beers
            } else {
                Repository.beers.filter { it.beercolor == selectedTab.beercolor }
            }
            lazyliststate.scrollToItem(0)
        }
    }

}