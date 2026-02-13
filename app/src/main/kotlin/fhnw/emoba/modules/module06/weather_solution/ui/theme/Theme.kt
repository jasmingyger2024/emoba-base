package fhnw.emoba.modules.module06.weather_solution.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val appDarkColors = darkColorScheme(
//    primary = gray500,
//    primaryVariant = gray600,
//    secondary = blue400,
    primaryContainer = Color(0xFF303030)

)

private val appLightColors = lightColorScheme(
    //Background colors
    primary = lightBlue400,
    primaryContainer = lightBlue500,
    secondary = amber400,

    secondaryContainer = Color(0xFF03DAC6),
    background = Color(0xFFF1F5FE),
    surface = Color(0xFFFFFFFA), //Color(0xFFF8FCFD),
    error = Color(0xFFB00020),

    //Typography and icon colors
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

@Composable
fun WeatherAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        appDarkColors
    } else {
        appLightColors
    }

    MaterialTheme(
        colorScheme = colors,
        shapes = shapes,
        content = content
    )
}