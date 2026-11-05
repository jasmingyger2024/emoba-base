package fhnw.emoba.modules.module04.countries_solution.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fhnw.emoba.modules.module04.countries_solution.data.Country
import fhnw.emoba.modules.module04.countries_solution.viewmodel.CountriesModel
import java.util.Locale

@Composable
fun CountriesUI(viewModel: CountriesModel) {
    //Alternative falls man nur wenig am Theme aendern will
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary   = Color(0xFF003399),
            onPrimary = Color(0xFFFFCC00),
            surface   = Color(0xFF5296B4), //z.B. die Backgroundfarbe der TopAppBar innerhalb des Scaffolds
            surfaceVariant = Color(0xFFE6CB6A), //z.B. die Backgroundfarbe der Card
        )
    ) {
        Scaffold(topBar  = { Bar(viewModel) },
                 content = { Body(viewModel, it) })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(viewModel: CountriesModel){
    with(viewModel){
        TopAppBar(title  = { Text(title) },
                  colors = TopAppBarDefaults.topAppBarColors(containerColor    = MaterialTheme.colorScheme.primaryContainer,
                                                             titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer))
    }
}

@Composable
private fun Body(viewModel: CountriesModel, paddingValues: PaddingValues) {
    with(viewModel){
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(items = countries,
                  key   = { it.name }) {
                CountryPane(country = it)
            }
        }
    }
}

@Composable
private fun CountryPane(country: Country) {
    with(country) {
        Card(
            modifier  = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp, bottom = 18.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier            = Modifier.padding(10.dp),
                   verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Heading(name)
                Box(modifier = Modifier.fillMaxWidth()) {
                    Subheading(text     = area.format("%,.1f km²"),
                               modifier = Modifier.align(Alignment.CenterStart))
                    Subheading(text     = population.format("%,d Einw."),
                               modifier = Modifier.align(Alignment.CenterEnd))
                }
            }
        }
    }
}

@Composable
private fun Heading(text: String) {
    Text(text  = text,
        fontWeight = FontWeight.Bold,
         style = MaterialTheme.typography.titleMedium)
}

@Composable
private fun Subheading(text: String, modifier: Modifier) {
    Text(text     = text,
         style    = MaterialTheme.typography.labelLarge,
         modifier = modifier.padding(vertical = 24.dp)
    )
}

// eine  praktische Extension-Function zum Formatieren einer Zahl
private fun Number.format(formatPattern: String) = formatPattern.format(CH, this)

private val CH = Locale.Builder().setLanguage("de").setRegion("CH").build()


@Preview(name = "Single Country Card")
@Composable
private fun Preview(){
    val lummerland = Country("Lummerland", 0.12, 5)
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary   = Color(0xFF003399),
            onPrimary = Color(0xFFFFCC00),
            surface   = Color(0xFF5296B4), //z.B. die Backgroundfarbe der TopAppBar innerhalb des Scaffolds
            surfaceVariant = Color(0xFFE6CB6A), //z.B. die Backgroundfarbe der Card
        )
    ) {
        CountryPane(lummerland)
    }

}