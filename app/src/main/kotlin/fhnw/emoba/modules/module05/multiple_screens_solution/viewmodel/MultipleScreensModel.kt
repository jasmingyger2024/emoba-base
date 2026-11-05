package fhnw.emoba.modules.module05.multiple_screens_solution.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MultipleScreensModel : ViewModel() {
    var currentScreenId by mutableStateOf(ScreenId.HOME)
}