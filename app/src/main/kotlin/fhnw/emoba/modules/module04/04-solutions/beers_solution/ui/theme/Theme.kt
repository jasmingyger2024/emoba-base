package fhnw.emoba.modules.module04.beers_solution.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val darkColors = darkColorScheme(
    //Background colors
    primary            = amber800,
    primaryContainer   = amber800,
    secondary          = teal300,
    secondaryContainer = teal900,
    background         = gray800,
    surface            = gray600,
    error              = amber900,
    
    //Typography and icon colors
    onPrimary        = gray900,
    onSecondary      = gray900,
    onBackground     = gray100,
    onSurface        = gray100,
    onError          = gray900,
)

private val lightColors = lightColorScheme(
    //Background colors
    primary            = amberA700,
    primaryContainer   = amberA700,
    secondary          = amber500,
    secondaryContainer = amber400,
    background         = gray300,
    surface            = gray100,
    error              = amber900,

    //Typography and icon colors
    onPrimary        = gray100,
    onSecondary      = gray100,
    onBackground     = gray900,
    onSurface        = gray900,
    onError          = gray100,
)

@Composable
fun BeersTheme(darkTheme: Boolean, content: @Composable() () -> Unit) {
    MaterialTheme(
            colorScheme = if (darkTheme) darkColors else lightColors,
            typography  = typography,
            shapes      = shapes,
            content     = content
    )
}