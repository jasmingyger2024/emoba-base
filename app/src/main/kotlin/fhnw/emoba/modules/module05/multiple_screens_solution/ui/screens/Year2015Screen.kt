package fhnw.emoba.modules.module05.multiple_screens_solution.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fhnw.emoba.modules.module05.multiple_screens_solution.viewmodel.ScreenId

@Composable
fun Year2015Screen(
    navigateBack: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToScreen: (ScreenId) -> Unit
) {
    DefaultScreen(
        screenId = ScreenId.YEAR_2015,
        lastScreenId = ScreenId.YEAR_1955,
        navigateToHome = navigateToHome,
        navigateToLastScreen = { navigateToScreen(ScreenId.YEAR_1955) }
    )

    BackHandler(enabled = true){
        navigateBack()
    }
}

@Preview
@Composable
private fun ScreenPreview(){
    Year2015Screen(navigateBack = {}, navigateToHome = {}, navigateToScreen = {})
}