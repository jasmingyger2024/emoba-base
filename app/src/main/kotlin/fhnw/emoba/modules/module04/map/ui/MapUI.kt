package fhnw.emoba.modules.module04.map.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MapsHomeWork
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fhnw.emoba.R
import fhnw.emoba.modules.module04.map.viewmodel.MapModel
import org.ramani.compose.MapLibre
import org.ramani.compose.Margins
import org.ramani.compose.Symbol
import org.ramani.compose.UiSettings

@Composable
fun MapUI(viewModel: MapModel) {
    MaterialTheme {
        Scaffold(topBar    = { Top(viewModel) },
            content   = { Body(viewModel, it) },
            bottomBar = { Bottom(viewModel) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Top(viewModel: MapModel) {
    with(viewModel) {
        TopAppBar(title = {Text(text  = title,
            style = MaterialTheme.typography.headlineMedium) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor    = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer),
        )
    }
}

@Composable
private fun Body(viewModel: MapModel, paddingValues: PaddingValues) {
    with(viewModel) {
        Box(modifier = Modifier.padding(paddingValues)
            .fillMaxSize())
        {
            MapLibre(
                modifier = Modifier.fillMaxSize(),
                uiSettings = UiSettings(
                    logoMargins = Margins(left = 60)
                ),
                styleBuilder = mapStyle,
                cameraPosition = mapCameraPosition,
            )
            {
                Symbol(
                    center = basel,
                    imageId = R.drawable.pinblue
                )
                //Todo: also set a marker pin to Brugg Campus - use a Symbol Composable and a red pin

            }
        }
    }
}

@Composable
fun Bottom(viewModel: MapModel) {
    BottomAppBar {
        IconButton(modifier = Modifier.fillMaxWidth(),
            onClick  = { viewModel.resetMapToBrugg() })
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            )
            {
                Icon(
                    imageVector        = Icons.Filled.MapsHomeWork,
                    contentDescription = "Show Brugg",
                    modifier = Modifier.padding(8.dp)
                )
                Text(text = "Todo: show Brugg")
            }
        }
    }
}