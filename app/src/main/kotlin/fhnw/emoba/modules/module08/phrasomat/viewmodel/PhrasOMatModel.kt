package fhnw.emoba.modules.module08.phrasomat.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fhnw.emoba.modules.module08.phrasomat.data.generator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class PhrasOMatModel : ViewModel() {
    val title = "PhrasOMat"

    val phrases = mutableStateListOf<String>()

    fun nextPhrase() {
        viewModelScope.launch(Dispatchers.Default) {
            phrases.add(generator.generatePhase())
            }
        }
    }

