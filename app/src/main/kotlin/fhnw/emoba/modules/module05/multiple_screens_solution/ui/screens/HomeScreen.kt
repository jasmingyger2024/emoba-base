package fhnw.emoba.modules.module05.multiple_screens_solution.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import fhnw.emoba.modules.module05.multiple_screens_solution.viewmodel.ScreenId
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navigateToScreen: (ScreenId) -> Unit,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                content = { Drawer(onScreenClick = navigateToScreen) }
            )
        },
        content = {
            Scaffold(
                topBar = { HomeTopBar(drawerState, navigateToScreen) },
                content = {
                    DefaultBody(ScreenId.HOME, it)
                }
            )
        }
    )

    // BackHandler um Drawer zu schliessen (optional) - ohne dies wird App geschlossen resp. in Hintergrund gelegt
    BackHandler(enabled = true) {
        scope.launch { // ...benötigt die Verwendung von Coroutines - dazu kommen wir nächste Woche
            drawerState.close()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar(drawerState: DrawerState, navigateToScreen: (ScreenId) -> Unit) {
        TopAppBar(
            title = { Text(ScreenId.HOME.title) },
            navigationIcon = { DrawerIcon(drawerState = drawerState) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            actions = {
                IconButton(onClick = { navigateToScreen(ScreenId.YEAR_1955) }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, "nextScreen")
                }
            }
        )
}