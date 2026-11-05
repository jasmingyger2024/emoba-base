package fhnw.emoba.modules.module05.multiple_screens_solution.ui

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fhnw.emoba.modules.module05.multiple_screens_solution.ui.screens.AboutScreen
import fhnw.emoba.modules.module05.multiple_screens_solution.ui.screens.HomeScreen
import fhnw.emoba.modules.module05.multiple_screens_solution.ui.screens.Year1955Screen
import fhnw.emoba.modules.module05.multiple_screens_solution.ui.screens.Year2015Screen
import fhnw.emoba.modules.module05.multiple_screens_solution.viewmodel.MultipleScreensModel
import fhnw.emoba.modules.module05.multiple_screens_solution.viewmodel.ScreenId

@Composable
fun MultipleScreensUI(viewModel: MultipleScreensModel) {
    MaterialTheme {
        with(viewModel) {
                Crossfade(
                    targetState = currentScreenId,
                    animationSpec = tween(durationMillis = 3000, easing = Ease), // das Easing ist bei Animationen entscheidender als bei reinen Transitionen
                ) { screen ->
                    when (screen) {
                        ScreenId.HOME -> HomeScreen(
                            navigateToScreen = { screen ->
                                viewModel.currentScreenId = screen
                            }
                        )

                        ScreenId.YEAR_1955 -> Year1955Screen(
                            navigateBack = {
                                viewModel.currentScreenId = ScreenId.HOME
                            },
                            navigateToHome = {
                                viewModel.currentScreenId = ScreenId.HOME
                            },
                            navigateToScreen = { screen ->
                                viewModel.currentScreenId = screen
                            }
                        )

                        ScreenId.YEAR_2015 -> Year2015Screen(
                            navigateBack = {
                                viewModel.currentScreenId = ScreenId.YEAR_1955
                            },
                            navigateToHome = {
                                viewModel.currentScreenId = ScreenId.HOME
                            },
                            navigateToScreen = { screen ->
                                viewModel.currentScreenId = screen
                            }
                        )

                        ScreenId.ABOUT -> AboutScreen(
                            navigateBack = {
                                viewModel.currentScreenId = ScreenId.HOME
                            },
                            navigateToHome = {
                                viewModel.currentScreenId = ScreenId.HOME
                            },
                            navigateToScreen = { screen ->
                                viewModel.currentScreenId = screen
                            }
                        )
                    }
                }
            }
        }
    }

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun HomePreview() {
    val vm = MultipleScreensModel()
    vm.currentScreenId = ScreenId.HOME
    MultipleScreensUI(viewModel = vm)
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun Year_1955_Preview() {
    val vm = MultipleScreensModel()
    vm.currentScreenId = ScreenId.YEAR_1955
    MultipleScreensUI(viewModel = vm)
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun Year_2015_Preview() {
    val vm = MultipleScreensModel()
    vm.currentScreenId = ScreenId.YEAR_2015
    MultipleScreensUI(viewModel = vm)
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun About_Preview() {
    val vm = MultipleScreensModel()
    vm.currentScreenId = ScreenId.ABOUT
    MultipleScreensUI(viewModel = vm)
}