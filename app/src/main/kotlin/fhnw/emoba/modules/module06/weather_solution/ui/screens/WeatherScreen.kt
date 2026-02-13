package fhnw.emoba.modules.module06.weather_solution.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import fhnw.emoba.modules.module06.weather_solution.data.City
import fhnw.emoba.modules.module06.weather_solution.data.Forecast
import fhnw.emoba.modules.module06.weather_solution.data.Hourly
import fhnw.emoba.modules.module06.weather_solution.ui.theme.WeatherAppTheme
import fhnw.emoba.modules.module06.weather_solution.viewmodel.ScreenId
import fhnw.emoba.modules.module06.weather_solution.viewmodel.WeatherModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private val CH = Locale.Builder().setLanguage("de").setRegion("CH").build()

@Composable
fun WeatherScreen(
    title: String,
    isDarkTheme: Boolean,
    isLoading: Boolean,
    forecasts: List<Forecast>,
    toggleTheme: () -> Unit,
    removeFavorite: (City) -> Unit,
    navigateToCities: () -> Unit
) {
    WeatherAppTheme(isDarkTheme) {
        Scaffold(
            topBar = { Bar(title, isDarkTheme, toggleTheme) },
            floatingActionButton = { FAB(navigateToCities) },
            floatingActionButtonPosition = FabPosition.End,
            content = {  Body(isLoading, forecasts, removeFavorite, it) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(
    title: String,
    isDarkTheme: Boolean,
    toggleTheme: () -> Unit,
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(title)
                IconToggleButton(checked = isDarkTheme, onCheckedChange = { toggleTheme() }) {
                    Icon(
                        imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                        contentDescription = "ThemeToggle"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
private fun Body(
    isLoading: Boolean,
    forecasts: List<Forecast>,
    removeFavorite: (City) -> Unit,
    paddingValues: PaddingValues
) {
    when {
        isLoading -> LoadingBox("Wetterdaten werden geladen")
        else      -> AllForecasts(forecasts, removeFavorite, paddingValues)
    }
}

@Composable
private fun AllForecasts(
    forecasts: List<Forecast>,
    removeFavorite: (City) -> Unit,
    paddingValues: PaddingValues
) {
    val state = rememberLazyListState()
    LazyColumn(state   = state,
              modifier = Modifier.padding(paddingValues))
    {
        items(items = forecasts, key = { it.city.id } ) { ForeCastPanel(it, removeFavorite) }
    }
}

@Composable
private fun ForeCastPanel(forecast: Forecast, removeFavorite: (City) -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            with(forecast.current) {
                InfoLine(
                    "${dateTime.asTime()} ${dateTime.asDate()}",
                    "\u263C \u2191${sunrise}  \u2193${sunset}",
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                SubHeading(forecast.city, removeFavorite)
                InfoLine {
                    Heading(temperature.asText("%.1f°"))
                    Image(weatherImage, "")
                }
                InfoLine(
                    clouds.asText("\u2601 %.0f%%"),
                    rain.asText("\u2602 %.1f mm"),
                    windSpeed.asText("%.1f m/s"),
                    windDeg.asText("%.0f°")
                )
                InfoLine(humidity.asText("%.0f%%"), pressure.asText("%.0f hPa"))
            }
            HorizontalDivider(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
            AllHourlyForecasts(forecast.nextHours)
        }
    }
}

@Composable
private fun AllHourlyForecasts(hourlyList: List<Hourly>) {
    val state = rememberLazyListState()
    LazyRow(state = state) {
        items(hourlyList) { HourlyPanel(it) }
    }
}

@Composable
private fun HourlyPanel(hourly: Hourly) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .padding(5.dp)
    ) {
        Surface {
            Column(modifier = Modifier.padding(5.dp)) {
                with(hourly) {
                    InfoLine(dateTime.asTime(), dateTime.asText("d.M."))
                    InfoLine {
                        SubSubHeading(temperature.asText("%.1f°"))
                        Image(weatherImage, "")
                    }
                    InfoLine((pop * 100).asText("\u2602 %.0f%%"), clouds.asText("\u2601 %.0f%%"))
                    InfoLine(windSpeed.asText("%.1f m/s"), windDeg.asText("%.0f°"))
                }
            }
        }
    }
}

@Composable
private fun FAB(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Filled.AddLocation, "AddCity")
    }
}


// Kandidaten fuer wiederverwendbare Funktionen

@Composable
private fun Heading(text: String) = StyledTextLine(text, MaterialTheme.typography.titleMedium)

@Composable
private fun SubHeading(city: City, removeFavorite: (City) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StyledTextLine(city.name, MaterialTheme.typography.titleMedium)
        IconButton(onClick = { removeFavorite(city) }) {
            Icon(imageVector = Icons.Default.RemoveCircle, contentDescription = "DarkMode")
        }
    }
}

@Composable
private fun SubSubHeading(text: String) = StyledTextLine(text, MaterialTheme.typography.titleSmall)

@Composable
private fun StyledTextLine(text: String, style: TextStyle) {
    Text(
        text = text,
        style = style,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun LoadingBox(message: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(message, style = MaterialTheme.typography.titleSmall)
        CircularProgressIndicator(modifier = Modifier.padding(10.dp))
    }
}

@Composable
private fun InfoLine(vararg text: String, modifier: Modifier = Modifier) {
    InfoLine(modifier) {
        text.map { Text(it) }
    }
}

@Composable
private fun InfoLine(modifier: Modifier = Modifier, content: @Composable (RowScope.() -> Unit)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        content = content
    )
}


private fun LocalDateTime.asText(pattern: String): String =
    format(DateTimeFormatter.ofPattern(pattern))

private fun LocalDateTime.asDate() = asText("d.M.yy")

private fun LocalDateTime.asTime() = asText("HH:mm")

private fun Number.asText(pattern: String): String = pattern.format(CH, this)