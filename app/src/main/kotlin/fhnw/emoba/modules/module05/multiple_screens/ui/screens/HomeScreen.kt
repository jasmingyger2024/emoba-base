package fhnw.emoba.modules.module05.multiple_screens.ui.screens

import android.annotation.SuppressLint
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
import androidx.compose.ui.tooling.preview.Preview
import fhnw.emoba.modules.module05.multiple_screens.viewmodel.MultipleScreensModel
import fhnw.emoba.modules.module05.multiple_screens.viewmodel.ScreenId

@Composable
fun HomeScreen(viewModel: MultipleScreensModel) {
    //Todo: jedem Screen nur das nötige mitgeben, nicht immer das ganze viewModel

    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                content = { Drawer(viewModel) }
            )
        },
        content = {
            Scaffold(
                topBar = { HomeTopBar(viewModel, drawerState) },
                content = {
                    DefaultBody(ScreenId.HOME, it)
                }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar(viewModel: MultipleScreensModel, drawerState: DrawerState) {
    with(viewModel) {
        TopAppBar(title          = { Text(ScreenId.HOME.title) },
                  navigationIcon = { DrawerIcon(drawerState = drawerState) },
                  colors         = TopAppBarDefaults.topAppBarColors(
                      containerColor = MaterialTheme.colorScheme.primaryContainer
                  ),
                  actions        = { IconButton(onClick = {currentScreenId = ScreenId.YEAR_1955}) { //Todo: auf naechsten Screen wechseln
                                     Icon(Icons.AutoMirrored.Filled.ArrowForward, "nextScreen")
                }
            }
        )
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
fun Preview() {
    HomeScreen(MultipleScreensModel())
}