package fhnw.emoba.modules.module05.multiple_screens.ui.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fhnw.emoba.modules.module05.multiple_screens.viewmodel.MultipleScreensModel
import fhnw.emoba.modules.module05.multiple_screens.viewmodel.ScreenId

@Composable
fun Year1955Screen(viewModel: MultipleScreensModel) {
    //Todo: jedem Screen nur das nötige mitgeben, nicht immer das ganze viewModel

    DefaultScreen(
        viewModel = viewModel,
        screenId = ScreenId.YEAR_1955,
        lastScreenId = ScreenId.HOME,
        nextScreenId = ScreenId.YEAR_2015
    )

    BackHandler(enabled = true) {
        //Todo: zurück in die Gegenwart (nach Hause)
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun ScreenPreview() {
    Year1955Screen(viewModel = MultipleScreensModel())
}