package fhnw.emoba.modules.module04.tabs.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel

class TabsModel : ViewModel() {
    val title = "Tabs Example App"
    
    //Todo: was wird benoetigt fuer den Praesentationszustand?
    var selectedTab by mutableStateOf(TabId.FIRST)


}