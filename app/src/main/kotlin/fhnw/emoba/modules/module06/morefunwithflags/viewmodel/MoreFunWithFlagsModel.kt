package fhnw.emoba.modules.module06.morefunwithflags.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import fhnw.emoba.modules.module06.morefunwithflags.data.FlagService
import fhnw.emoba.modules.module06.morefunwithflags.data.FlagSize

class MoreFunWithFlagsModel : ViewModel() {
    val title = "More Fun With Flags"
    var countryCode by mutableStateOf("")
    var flagService: FlagService? = null
    
    var flag by mutableStateOf(FlagSize.x550.defaultImage)
    
    fun loadFlag() {
        flag = flagService?.requestFlag(countryCode, FlagSize.x550) ?: FlagSize.x550.defaultImage
    }
}