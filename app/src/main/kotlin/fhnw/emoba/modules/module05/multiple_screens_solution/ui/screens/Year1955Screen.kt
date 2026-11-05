package fhnw.emoba.modules.module05.multiple_screens_solution.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fhnw.emoba.modules.module05.multiple_screens_solution.viewmodel.ScreenId

@Composable
fun Year1955Screen(
    navigateBack: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToScreen: (ScreenId) -> Unit,
) {
    DefaultScreen(
        screenId = ScreenId.YEAR_1955,
        lastScreenId = ScreenId.HOME,
        nextScreenId = ScreenId.YEAR_2015,
        navigateToHome = navigateToHome,
        navigateToLastScreen = { navigateToScreen(ScreenId.HOME) },
        navigateToNextScreen = { navigateToScreen(ScreenId.YEAR_2015) }
    )

    BackHandler(enabled = true){
        navigateBack()
    }
}

@Preview
@Composable
private fun ScreenPreview(){
    Year1955Screen(navigateBack = {}, navigateToHome = {}, navigateToScreen = {})
}