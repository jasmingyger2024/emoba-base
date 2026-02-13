package fhnw.emoba.modules.module09.gps.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val appLightColors = lightColorScheme(
    //Background colors
    primary          = blue500,
    primaryContainer = lightBlue600,
    secondary        = amber500,

    secondaryContainer = teal200,
    background       = gray100,
    surface          = gray300,
    error            = amber900,

    //Typography and icon colors
    onPrimary        = lightBlue50,
    onSecondary      = Color.Black,
    onBackground     = Color.Black,
    onSurface        = Color.Black,
    onError          = Color.White,
)

@Composable
fun GpsAppTheme(content: @Composable() () -> Unit) {

    MaterialTheme(
            colorScheme = appLightColors,
            typography  = typography,
            shapes      = shapes,
            content     = content
    )
}