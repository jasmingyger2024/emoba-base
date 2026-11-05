package fhnw.emoba.modules.module05.multiple_screens.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import fhnw.emoba.modules.module05.multiple_screens.ui.screens.AboutScreen
import fhnw.emoba.modules.module05.multiple_screens.ui.screens.HomeScreen
import fhnw.emoba.modules.module05.multiple_screens.ui.screens.Year1955Screen
import fhnw.emoba.modules.module05.multiple_screens.ui.screens.Year2015Screen
import fhnw.emoba.modules.module05.multiple_screens.viewmodel.MultipleScreensModel
import fhnw.emoba.modules.module05.multiple_screens.viewmodel.ScreenId


@Composable
fun MultipleScreensUI(viewModel: MultipleScreensModel) {
    with(viewModel) {
        MaterialTheme {
            //Todo: Crossfade verwenden um die verschiedenen Screens anzuzeigen
            if(viewModel.currentScreenId == ScreenId.HOME){
                //Todo: jedem Screen nur das nötige mitgeben, nicht immer das ganze viewModel
                HomeScreen(viewModel)
            }
            else {
                AboutScreen(viewModel)
            }


            //https://developer.android.com/reference/kotlin/androidx/compose/animation/package-summary#crossfade
        }
    }
}