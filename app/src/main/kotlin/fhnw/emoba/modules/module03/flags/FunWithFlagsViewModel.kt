package fhnw.emoba.modules.module03.flags

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import fhnw.emoba.R

class FunWithFlagsViewModel : ViewModel() {
    var image by mutableStateOf(R.drawable.sheldon)

    var slowakei by mutableStateOf(R.drawable.slowakei)
    var tschechien by mutableStateOf(R.drawable.tschechien)

}