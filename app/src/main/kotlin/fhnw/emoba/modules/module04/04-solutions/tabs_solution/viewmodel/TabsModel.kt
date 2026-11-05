package fhnw.emoba.modules.module04.tabs_solution.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TabsModel : ViewModel() {
    val title = "Tabs Example App"
    
    var selectedTab by mutableStateOf(TabId.FIRST)
}