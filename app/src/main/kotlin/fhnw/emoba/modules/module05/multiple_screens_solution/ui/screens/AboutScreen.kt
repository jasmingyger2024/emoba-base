package fhnw.emoba.modules.module05.multiple_screens_solution.ui.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
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
import fhnw.emoba.modules.module05.multiple_screens_solution.viewmodel.ScreenId

@Composable
fun AboutScreen(
    navigateBack: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToScreen: (ScreenId) -> Unit
) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                content = { Drawer(onScreenClick = navigateToScreen) }
            )
        },
        content = {
            Scaffold(
                topBar = { AboutTopBar(drawerState) },
                floatingActionButton = { GoHomeFAB(onClick = navigateToHome) },
                floatingActionButtonPosition = FabPosition.End,
                content = {
                    DefaultBody(ScreenId.ABOUT, it)
                }
            )
        }
    )

    BackHandler(enabled = true){
        navigateBack()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AboutTopBar(drawerState: DrawerState) {
    TopAppBar(title          = { Text(ScreenId.ABOUT.title) },
              colors         = TopAppBarDefaults.topAppBarColors(
                  containerColor = MaterialTheme.colorScheme.tertiaryContainer
              ),
              navigationIcon = { DrawerIcon(drawerState) }
    )
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun ScreenPreview(){
    AboutScreen(navigateBack = {}, navigateToHome = {}, navigateToScreen = {})
}