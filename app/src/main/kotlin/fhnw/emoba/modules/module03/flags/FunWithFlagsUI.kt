package fhnw.emoba.modules.module03.flags

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


@Composable
fun FunWithFlagsUI(viewModel: FunWithFlagsViewModel) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                content = { Drawer(viewModel) }
            )
        }, content = {
            Scaffold(topBar = { Bar(viewModel, drawerState) },
                content = { Body(viewModel, it)})
        })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bar(viewModel: FunWithFlagsViewModel, drawerState: DrawerState) {
    with(viewModel) {
        TopAppBar(title    = { Text("Fun with Flags",
            fontSize = 20.sp //hier wird die Fontsize explizit gesetzt und wird somit nicht durch die biggerFont Einstellung beeinflusst
        ) },
            colors         = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer),
            navigationIcon = { DrawerIcon(drawerState) })
    }
}

@Composable
fun Body(viewModel: FunWithFlagsViewModel, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(viewModel.slowakei),
            contentDescription = "Slowakische Flagge",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Image(
            painter = painterResource(viewModel.tschechien),
            contentDescription = "Tschechische Flagge",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }
}


@Composable
private fun Drawer(viewModel: FunWithFlagsViewModel) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(viewModel.image),
            contentDescription = "Sheldon holding a flag",
            modifier = Modifier.align(Alignment.Center) // dieser modifier funktioniert nur wenn das image in einer Box, Column oder einer Row ist!
        )
    }
}

@Composable
private fun DrawerIcon(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    IconButton(onClick = { scope.launch { drawerState.open() } }) {
        Icon(Icons.Filled.Menu, "Drawer öffnen")
    }
}