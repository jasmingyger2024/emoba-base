package fhnw.emoba.modules.module06.weather_solution.ui.screens

import android.view.KeyEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import fhnw.emoba.modules.module06.weather_solution.data.City
import fhnw.emoba.modules.module06.weather_solution.ui.theme.WeatherAppTheme
import fhnw.emoba.modules.module06.weather_solution.viewmodel.ScreenId
import fhnw.emoba.modules.module06.weather_solution.viewmodel.WeatherModel
import java.util.Locale

private val CH = Locale.Builder().setLanguage("de").setRegion("CH").build()


@Composable
fun CitiesScreen(
    title: String,
    isDarkTheme: Boolean,
    searchText: String,
    countryCH: Boolean,
    countryDE: Boolean,
    currentCityList: List<City>,
    totalCityCount: Int,
    onSearchTextChanged: (String) -> Unit,
    launchSearch: () -> Unit,
    toggleCountryCH: () -> Unit,
    toggleCountryDE: () -> Unit,
    addToFavorite: (City) -> Unit,
    navigateBack: () -> Unit,
) {
    WeatherAppTheme(isDarkTheme) {
        Scaffold(
            topBar = { TopBar(title, navigateBack) },
            content = { padding ->
                Body(
                    searchText = searchText,
                    countryCH = countryCH,
                    countryDE = countryDE,
                    currentCityList = currentCityList,
                    totalCityCount = totalCityCount,
                    onSearchTextChanged = onSearchTextChanged,
                    launchSearch = launchSearch,
                    toggleCountryCH = toggleCountryCH,
                    toggleCountryDE = toggleCountryDE,
                    addToFavorite = addToFavorite,
                    navigateBack = navigateBack,
                    paddingValues = padding
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    navigateBack: () -> Unit,
) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
            }
        }
    )
}


@Composable
private fun Body(
    searchText: String,
    countryCH: Boolean,
    countryDE: Boolean,
    currentCityList: List<City>,
    totalCityCount: Int,
    onSearchTextChanged: (String) -> Unit,
    launchSearch: () -> Unit,
    toggleCountryCH: () -> Unit,
    toggleCountryDE: () -> Unit,
    addToFavorite: (City) -> Unit,
    navigateBack: () -> Unit,
    paddingValues: PaddingValues
) {
    ConstraintLayout(
        Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        val (citiesList, citiesChooser, footer) = createRefs()

        CitySearch(
            searchText = searchText,
            countryCH = countryCH,
            countryDE = countryDE,
            onSearchTextChanged = onSearchTextChanged,
            launchSearch = launchSearch,
            toggleCountryCH = toggleCountryCH,
            toggleCountryDE = toggleCountryDE,
            modifier = Modifier.constrainAs(citiesChooser) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )
        CityList(
            currentCityList = currentCityList,
            addToFavorite = addToFavorite,
            navigateBack = navigateBack,
            modifier = Modifier.constrainAs(citiesList) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(citiesChooser.bottom, 20.dp)
                bottom.linkTo(footer.bottom, 5.dp)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        )
        Footer(
            currentCityCount = currentCityList.size,
            totalCityCount = totalCityCount,
            modifier = Modifier.constrainAs(footer) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )
    }
}

@Composable
private fun CitySearch(
    searchText: String,
    countryCH: Boolean,
    countryDE: Boolean,
    onSearchTextChanged: (String) -> Unit,
    launchSearch: () -> Unit,
    toggleCountryCH: () -> Unit,
    toggleCountryDE: () -> Unit,
    modifier: Modifier
) {
    val keyboard = LocalSoftwareKeyboardController.current
    Card(modifier) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { onSearchTextChanged(it) },
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = {
                        keyboard?.hide()
                        onSearchTextChanged("")
                        launchSearch()
                    }) {
                        Icon(Icons.Filled.Clear, "löschen")
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        keyboard?.hide()
                        launchSearch()
                    },
                    onDone = {
                        keyboard?.hide()
                        launchSearch()
                    }
                ),
                placeholder = { Text("Suche") },
                modifier = Modifier
                    .fillMaxWidth()
                    .onKeyEvent {
                        if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                            val updatedSearchText = searchText.replace("\n", "", ignoreCase = true)
                            onSearchTextChanged(updatedSearchText)
                        }
                        launchSearch()
                        true
                    }
            )
            Preference("Schweiz") { ChSwitch(countryCH, toggleCountryCH) }
            Preference("Deutschland") { DeSwitch(countryDE, toggleCountryDE) }
        }
    }
}

@Composable
private fun CityList(
    currentCityList: List<City>,
    addToFavorite: (City) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier
) {
    Box(modifier) {
        if (currentCityList.isEmpty()) {
            Text(
                text = "Keine Städte gefunden",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(10.dp)
            )
        } else {
            HorizontalDivider(modifier = Modifier.align(Alignment.TopStart))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(
                    items = currentCityList,
                    key = { it.id }) {
                    CityCard(it, addToFavorite, navigateBack)
                }
            }
        }
    }
}

@Composable
private fun ChSwitch(countryCH: Boolean, toggleCountryCH: () -> Unit) {
    Switch(checked = countryCH, onCheckedChange = { toggleCountryCH() })
}

@Composable
private fun DeSwitch(countryDE: Boolean, toggleCountryDE: () -> Unit) {
    Switch(checked = countryDE, onCheckedChange = { toggleCountryDE() })
}

@Composable
private fun Preference(label: String, control: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label)
        control()
    }
}


@Composable
private fun CityCard(city: City, addToFavorite: (City) -> Unit, navigateBack: () -> Unit) {
    Card(Modifier.padding(bottom = 10.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            with(city) {
                Text(
                    text = countryCode,
                    style = MaterialTheme.typography.labelSmall,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    Arrangement.SpaceBetween
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Button(onClick = {
                        addToFavorite(city)
                        navigateBack()
                    }
                    ) { Icon(Icons.Filled.PlusOne, "Add") }
                }
                Text(
                    text = coordinates.toString(),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun Footer(currentCityCount: Int, totalCityCount: Int, modifier: Modifier) {
    Text(
        text = "%,d/%,d".format(CH, currentCityCount, totalCityCount),
        style = MaterialTheme.typography.labelSmall,
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 15.dp, vertical = 5.dp)
    )
}