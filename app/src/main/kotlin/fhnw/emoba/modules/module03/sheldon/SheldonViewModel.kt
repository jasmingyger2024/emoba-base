package fhnw.emoba.modules.module03.sheldon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import fhnw.emoba.R

class SheldonViewModel : ViewModel() {
    var image by mutableStateOf(R.drawable.sheldon)

}