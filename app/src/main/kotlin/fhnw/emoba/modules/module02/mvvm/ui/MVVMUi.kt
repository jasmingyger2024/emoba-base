package fhnw.emoba.modules.module02.mvvm.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MVVMUi() {  //Todo: Jeder View braucht ein ViewModel
    Scaffold(topBar                       = { Bar() },
             floatingActionButton         = { FAB() },
             floatingActionButtonPosition = FabPosition.End,
             content                      = { Body(it) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar() { //Todo: Jeder View braucht ein ViewModel
    TopAppBar(title  = { Text("to be replaced") },
              colors = TopAppBarDefaults.topAppBarColors(containerColor    = MaterialTheme.colorScheme.primaryContainer,
                                                         titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                  )
        )
}

@Composable
private fun Body(paddingValues: PaddingValues) { //Todo: Jeder View braucht ein ViewModel
    Box(modifier = Modifier.padding(paddingValues)
                           .padding(top    = 100.dp,
                                    bottom = 100.dp)
                           .fillMaxSize()) {
        Text(text     = "--",                               //Todo: Informationen werden im ViewModel verwaltet
             fontSize = 156.sp,
             modifier = Modifier.align(Alignment.TopCenter)
        )
        Button(onClick  = { },                              //Todo: Aktionen verändern das ViewModel und rufen dafür Funktionen des ViewModels auf
               modifier = Modifier.align(Alignment.BottomCenter)) {
            Text(text = "Click me")
        }
    }
}

@Composable
private fun FAB() { //Todo: Jeder View braucht ein ViewModel
    FloatingActionButton(onClick = {}) //Todo:  Geeignete Funktion im ViewModel implementieren und hier aufrufen.
    { Icon(Icons.Filled.Clear, "Clear") }
}