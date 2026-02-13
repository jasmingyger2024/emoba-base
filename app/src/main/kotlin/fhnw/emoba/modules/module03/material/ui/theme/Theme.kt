package fhnw.emoba.modules.module03.material.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val appDarkColors = darkColorScheme(
    //Background colors
    primary            = purple200,
    primaryContainer   = purple700,
    secondary          = teal200,
    secondaryContainer = blue200,
    background         = gray600,
    surface            = gray900,
    error              = amber900,

    //Typography and icon colors
    onPrimary        = Color.Black,
    onSecondary      = Color.Black,
    onBackground     = Color.White,
    onSurface        = Color.White,
    onError          = Color.Black,
)

private val appLightColors = lightColorScheme(
    //Background colors
    primary          = lightBlue500,
    primaryContainer = lightBlue600,
    secondary        = amber500,

    secondaryContainer = teal200,
    background       = gray300,
    surface          = gray500,
    error            = amber900,

    //Typography and icon colors
    onPrimary        = lightBlue50,
    onSecondary      = Color.Black,
    onBackground     = Color.Black,
    onSurface        = Color.Black,
    onError          = Color.White,
)

@Composable
fun MaterialAppTheme(darkTheme: Boolean, bigTypo: Boolean, content: @Composable() () -> Unit) {
    // customized Theme "MaterialAppTheme" using MaterialTheme with specific values:
    MaterialTheme(
            colorScheme = if (darkTheme) appDarkColors else appLightColors,
            typography  = if (bigTypo) typographyBig else typography,
            shapes      = shapes,
            content     = content
    )
}