package fhnw.emoba.modules.module05.coordinates.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fhnw.emoba.modules.module05.coordinates.viewmodel.CoordinatesModel

@Composable
fun CoordinatesUI(viewModel: CoordinatesModel){
    MaterialTheme(colorScheme = lightColorScheme(primary = Color(0xFFF3B423))) {
        Scaffold(topBar  = { Bar(viewModel.title) },
                 content = { padding ->
                     Body(
                         latitude = viewModel.latitude,
                         longitude = viewModel.longitude,
                         coordinates = viewModel.coordinates,
                         onLatitudeChanged = { viewModel.latitude = it },
                         onLongitudeChanged = { viewModel.longitude = it },
                         onConvertClicked = viewModel::convert,
                         paddingValues = padding
                     )
                 }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(title: String) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
    )
}

@Composable
private fun Body(
    latitude: Double,
    longitude: Double,
    coordinates: String,
    onLatitudeChanged: (Double) -> Unit,
    onLongitudeChanged: (Double) -> Unit,
    onConvertClicked: () -> Unit,
    paddingValues: PaddingValues
) {
        Column(modifier            = Modifier
            .padding(paddingValues)
            .padding(20.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.spacedBy(20.dp))
        {

            TextField(  value         = "$latitude",
                        onValueChange = { onLatitudeChanged(it.toDouble()) })

            TextField(  value         = "$longitude",
                        onValueChange = { newText -> onLongitudeChanged(newText.toDouble()) }) // gleich wie oben aber anstatt mit it einfach mit einer variable

            
            Button(onClick = onConvertClicked)                  //Todo: Warum braucht onConvertClicked keine Parameter und keinen Rueckgabewert?
            {
                Text("Format")
            }
            
            Box(modifier         = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
            {
                Text(coordinates, style = MaterialTheme.typography.bodyLarge)
            }
        }
}
