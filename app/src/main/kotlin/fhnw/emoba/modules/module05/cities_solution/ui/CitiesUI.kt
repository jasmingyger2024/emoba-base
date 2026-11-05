package fhnw.emoba.modules.module05.cities_solution.ui

import android.annotation.SuppressLint
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import fhnw.emoba.R
import fhnw.emoba.modules.module05.cities_solution.data.City
import fhnw.emoba.modules.module05.cities_solution.viewmodel.CitiesModel
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.ramani.compose.CameraPosition
import org.ramani.compose.MapLibre
import org.ramani.compose.Margins
import org.ramani.compose.Symbol
import org.ramani.compose.UiSettings
import java.util.Locale

private val CH = Locale.Builder().setLanguage("de").setRegion("CH").build()

@Composable
fun CitiesUI(viewModel: CitiesModel) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF01A2BE),
            onPrimary = Color(0xFF343434),
            primaryContainer = Color(0xFF164D65),
            onPrimaryContainer = Color(0xFFDCDCDC),
        )
    ) {
        Scaffold(
            topBar = { Bar(viewModel.title) },
            content = { padding ->
                Body(
                    cityFilter = viewModel.cityFilter,
                    onFilterChanged = { viewModel.cityFilter = it },
                    searchInCH = viewModel.searchInCH,
                    searchInDE = viewModel.searchInDE,
                    toggleSearchInCH = { viewModel.searchInCH = it },
                    toggleSearchInDE = { viewModel.searchInDE = it },
                    mapStyle = viewModel.mapStyle,
                    mapCameraPosition = viewModel.mapCameraPosition,
                    currentCityList = viewModel.currentCityList,
                    totalCityCount = viewModel.repo.data.size,
                    selectedCity = viewModel.selectedCity,
                    selectCity = viewModel::selectCity,
                    launchSearch = viewModel::launchSearch,
                    paddingValues = padding
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(title: String) {
    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
private fun Body(
    cityFilter: String,
    onFilterChanged: (String) -> Unit,
    searchInCH: Boolean,
    searchInDE: Boolean,
    toggleSearchInCH: (Boolean) -> Unit,
    toggleSearchInDE: (Boolean) -> Unit,
    mapStyle: Style.Builder,
    mapCameraPosition: CameraPosition,
    currentCityList: List<City>,
    totalCityCount: Int,
    selectedCity: City?,
    selectCity: (City) -> Unit,
    launchSearch: () -> Unit,
    paddingValues: PaddingValues
) {
    ConstraintLayout(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        val (searchForm, results, footer, mapcontainer) = createRefs()

        SearchForm(
            cityFilter = cityFilter,
            onFilterChanged = onFilterChanged,
            searchInCH = searchInCH,
            searchInDE = searchInDE,
            toggleSearchInCH = toggleSearchInCH,
            toggleSearchInDE = toggleSearchInDE,
            launchSearch = launchSearch,
            modifier = Modifier.constrainAs(searchForm) {
                top.linkTo(parent.top, 20.dp)
                start.linkTo(parent.start, 20.dp)
                end.linkTo(parent.end, 20.dp)
                width = Dimension.fillToConstraints
            }
        )
        SearchResults(
            currentCityList = currentCityList,
            selectedCity = selectedCity,
            selectCity = selectCity,
            modifier = Modifier.constrainAs(results) {
                top.linkTo(searchForm.bottom, 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(footer.top, 5.dp)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        )
        Footer(
            currentCityCount = currentCityList.size,
            totalCityCount = totalCityCount,
            modifier = Modifier.constrainAs(footer) {
                top.linkTo(results.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(mapcontainer.top)
                width = Dimension.fillToConstraints
            }
        )
        MapContainer(
            mapStyle = mapStyle,
            mapCameraPosition = mapCameraPosition,
            currentCityList = currentCityList,
            selectedCity = selectedCity,
            modifier = Modifier.constrainAs(mapcontainer) {
                top.linkTo(footer.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.percent(0.333F)
            }
        )
    }
}

@Composable
private fun SearchForm(
    cityFilter: String,
    onFilterChanged: (String) -> Unit,
    launchSearch: () -> Unit,
    searchInCH: Boolean,
    searchInDE: Boolean,
    toggleSearchInCH: (Boolean) -> Unit,
    toggleSearchInDE: (Boolean) -> Unit,
    modifier: Modifier
) {
    val keyboard = LocalSoftwareKeyboardController.current
    Card(modifier) {
        Column(Modifier.padding(10.dp)) {
            OutlinedTextField(value         = cityFilter,
                              onValueChange = { onFilterChanged(it) },
                              singleLine    = true,
                              trailingIcon  = {
                                  IconButton(
                                      onClick = {
                                        keyboard?.hide()
                                        onFilterChanged("")
                                        launchSearch()
                                      }
                                  ) {
                                      Icon(Icons.Filled.Clear, "löschen")
                                  }
                              },
                              keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                              keyboardActions = KeyboardActions(onSearch = { keyboard?.hide()
                                                                           launchSearch() }),
                              colors          = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.secondary),
                              placeholder     = { Text("Suche") },
                              modifier        = Modifier
                                  .fillMaxWidth()
                                  // das ist eigentlich nur fuer den Emulator interessant. Auf einem Smartphone wird kaum ein "Enter" eingegeben
                                                        .onKeyEvent {
                                                            if (it.nativeKeyEvent.keyCode == KEYCODE_ENTER) {
                                                                val updatedCityFiler = cityFilter.replace(
                                                                    "\n",
                                                                    "",
                                                                    ignoreCase = true
                                                                )
                                                                onFilterChanged(updatedCityFiler)
                                                                launchSearch()
                                                            }
                                                            false
                                                        })
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                CountryRow(name = "Schweiz",     launchSearch = launchSearch, checked = searchInCH, toggle = { toggleSearchInCH(it) })
                CountryRow(name = "Deutschland", launchSearch = launchSearch, checked = searchInDE, toggle = { toggleSearchInDE(it) })
            }
        }
    }
}

@Composable
private fun SearchResults(
    currentCityList: List<City>,
    selectedCity: City?,
    selectCity: (City) -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
//      Ausblick: Sobald die Daten asynchron geladen werden, kann der Lade-Vorgang visualisiert werden
//      if (isLoading) {
//          CircularProgressIndicator()
//      } else {
        if (currentCityList.isEmpty()) {
            Text(
                text = "Keine Städte gefunden",
                style = MaterialTheme.typography.titleSmall
            )
        } else {
            HorizontalDivider(modifier = Modifier.align(Alignment.TopStart))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(
                    items = currentCityList,
                    key = { it.id }) {
                    CityView(it, selectedCity, selectCity)
                }
            }
        }
//      }
    }
}

@Composable
private fun CityView(city: City, selectedCity: City?, selectCity: (City) -> Unit) {
    with(city) {
        val bgcolor: Color = if (selectedCity == city) {
            MaterialTheme.colorScheme.tertiaryContainer
        } else {
            MaterialTheme.colorScheme.onTertiary
        }
        ListItem(headlineContent   = { Text(name) },
                 overlineContent   = { Text(countryCode) },
                 supportingContent = { Text(coordinates.dms()) },
            modifier = Modifier.clickable {
                selectCity(city)
            },
            colors = ListItemDefaults.colors(containerColor = bgcolor)
        )
        HorizontalDivider()
    }
}

@Composable
private fun Footer(currentCityCount: Int, totalCityCount: Int, modifier: Modifier) {
    Text(text     = "%,d/%,d".format(CH, currentCityCount, totalCityCount),
         style    = MaterialTheme.typography.labelSmall,
         modifier = modifier
             .background(MaterialTheme.colorScheme.primary)
             .padding(horizontal = 15.dp, vertical = 5.dp))
}

@Composable
private fun MapContainer(
    mapStyle: Style.Builder,
    mapCameraPosition: CameraPosition,
    currentCityList: List<City>,
    selectedCity: City?,
    modifier: Modifier
) {
        MapLibre(
            modifier = modifier.fillMaxWidth(),
            uiSettings = UiSettings(
                logoMargins = Margins(left = 60)
            ),
            styleBuilder = mapStyle,
            cameraPosition = mapCameraPosition,
        )
        {
            //Insert a PointAnnotation composable function with the geographic coordinate to the content of MapboxMap composable function.
            for (city in currentCityList){
                val citypoint = LatLng(latitude = city.coordinates.latitude, longitude = city.coordinates.longitude)
                if (selectedCity == city){
                    Symbol(
                        center = citypoint,
                        imageId = R.drawable.pinred //clicked city is red marker
                    )
                } else {
                    Symbol(
                        center = citypoint,
                        imageId = R.drawable.pinblue //cities of result list are blue markers
                    )
                }
            }
        }
}

@Composable
private fun CountryRow(name: String, launchSearch: () -> Unit, checked: Boolean, toggle: (c: Boolean) -> Unit){
    val keyboard = LocalSoftwareKeyboardController.current
    
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .clickable(onClick = {
                toggle(!checked)
                launchSearch()
            })){
       Switch(checked = checked,
               onCheckedChange = {
                   toggle(it)
                   keyboard?.hide()
                   launchSearch()},
            modifier = Modifier
                .padding(end = 5.dp)
        )
        Text(text = name)
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun SearchFormPreview(){
    MaterialTheme(colorScheme = lightColorScheme(primary   = Color(0xFFEAE6DC),
                                       surface   = Color(0xFFF8F7F3),
                                       onPrimary = Color(0xFF343434),
                                       secondary = Color(0xFF347880)
    )
    ) {
        SearchForm(
            cityFilter = "Bern",
            onFilterChanged = {},
            searchInCH = true,
            searchInDE = false,
            toggleSearchInCH = {},
            toggleSearchInDE = {},
            launchSearch = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}