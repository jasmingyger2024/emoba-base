package fhnw.emoba.modules.module08.startrek.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val appLightColors = lightColorScheme(
    //Background colors
    primary          = trekkieMagenta,
    primaryContainer = trekkieMagenta,
    secondary        = amber500,

    secondaryContainer = teal200,
    background       = gray300,
    surface          = trekkieMagenta,
    error            = amber900,

    //Typography and icon colors
    onPrimary          = lightBlue50,
    onPrimaryContainer = Color.White,
    onSecondary        = Color.Black,
    onBackground       = Color.Black,
    onSurface          = Color.Black,
    onError            = Color.White,
)

@Composable
fun StarTrekAppTheme(content: @Composable() () -> Unit) {

    MaterialTheme(
            colorScheme = appLightColors,
            typography  = typography,
            shapes      = shapes,
            content     = content
    )
}