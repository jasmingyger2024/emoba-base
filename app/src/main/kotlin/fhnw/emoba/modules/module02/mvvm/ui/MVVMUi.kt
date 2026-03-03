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
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.modules.module02.mvvm.viewmodel.MVVMModel

@Composable
fun MVVMUi(viewModel: MVVMModel) {  //Todo: Jeder View braucht ein ViewModel
    Scaffold(topBar                       = { Bar(viewModel) },
             floatingActionButton         = { FAB(viewModel) },
             floatingActionButtonPosition = FabPosition.End,
             content                      = { Body(viewModel, it) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(viewModel: MVVMModel) { //Todo: Jeder View braucht ein ViewModel
    TopAppBar(title  = { Text("${viewModel.title} - ${viewModel.counter}") }, // String Interpolation: mit ${} wird der code der da ausgeführt wird in den String geschrieben. so kann man mehere Dinge zusammenhängen
              colors = TopAppBarDefaults.topAppBarColors(containerColor    = MaterialTheme.colorScheme.primaryContainer,
                                                         titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                  )
        )
}

@Composable
private fun Body(viewModel: MVVMModel, paddingValues: PaddingValues) { //Todo: Jeder View braucht ein ViewModel
    with(viewModel) { // so kann man sich untern viewModel. sparen
        Box(
            modifier = Modifier.padding(paddingValues)
                .padding(
                    top = 100.dp,
                    bottom = 100.dp
                )
                .fillMaxSize()
        ) {
            Text(
                text = counter.toString(),                               //Todo: Informationen werden im ViewModel verwaltet
                fontSize = 156.sp,
                modifier = Modifier.align(Alignment.TopCenter)
            )
            Button(
                onClick = { increase() },                              //Todo: Aktionen verändern das ViewModel und rufen dafür Funktionen des ViewModels auf
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text(text = "Click me")
            }
        }
    }
}

@Composable
private fun FAB(viewModel: MVVMModel) { //Todo: Jeder View braucht ein ViewModel // Floating Action Button
    FloatingActionButton(onClick = { viewModel.reset() }) //Todo:  Geeignete Funktion im ViewModel implementieren und hier aufrufen.
    { Icon(Icons.Filled.Clear, "Clear") }
}