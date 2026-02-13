package fhnw.emoba.modules.module05.squad.viewmodel

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import fhnw.emoba.modules.module05.squad.data.Squad
import fhnw.emoba.modules.module05.squad.data.SquadRepository

class SquadModel : ViewModel() {
    val title = "The Squad App"

    lateinit var squad: Squad

    fun loadRepoSquad(activity: ComponentActivity){
        //da Daten aus Repo nur einmalig eingelesen werden, auch bei neuer activity kein erneutes Einlesen nötig
        if (!this::squad.isInitialized) {
           squad = SquadRepository.loadSquad(activity)
        }
    }
}