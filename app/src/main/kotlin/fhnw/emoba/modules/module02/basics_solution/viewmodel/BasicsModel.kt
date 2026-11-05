package fhnw.emoba.modules.module02.basics_solution.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BasicsModel : ViewModel() {

    // "normale" Kotlin Variablen (für Read-Only UI)
    val title = "JetPack Compose Basics"
    val lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incidid unt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco aboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

    // Variabeln mit "by mutableStateOf" besprechen wir später noch genauer
    var textfieldtext by mutableStateOf("abc")
    var booleanValue by mutableStateOf(true)
}