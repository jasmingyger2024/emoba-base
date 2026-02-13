package fhnw.emoba.modules.module04.countries.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import fhnw.emoba.modules.module04.countries.viewmodel.CountriesModel
import java.util.Locale

@Composable
fun CountriesUI(viewModel: CountriesModel) {
    //Alternative falls man nur wenig am Theme aendern will
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary   = Color(0xFF003399),
            onPrimary = Color(0xFFFFCC00),
            surface   = Color(0xFF5296B4) //z.B. Background einer Card oder hier die Backgroundfarbe der TopAppBar innerhalb des Scaffolds
        )
    ) {
        Scaffold(
            topBar  = { Bar(viewModel) },
            content = { Body(viewModel, it) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(viewModel: CountriesModel){
    with(viewModel){
        TopAppBar(title = { Text(title) },
                 colors = TopAppBarDefaults.topAppBarColors(containerColor    = MaterialTheme.colorScheme.primaryContainer,
                                                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer))
    }
}

@Composable
private fun Body(viewModel: CountriesModel, paddingValues: PaddingValues) {
    Box(modifier         = Modifier.padding(paddingValues)
                                   .fillMaxSize(),
        contentAlignment = Alignment.Center){

        Text("To be replaced")
        
    //Todo: Verwenden Sie 'LazyColumn'
    // https://developer.android.com/reference/kotlin/androidx/compose/foundation/lazy/package-summary#lazycolumn
    }
}

//Todo: Implementieren Sie eine Funktion, die ein einzelnes Country visualisiert
//  verwenden Sie 'Card'
//  https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#card

//@Composable
//fun CountryPane(country: Country) {
//
//}


//Todo: verwenden Sie diese Funktion an geeigneter Stelle
@Composable
private fun Heading(text: String) {
    Text(text = text,
         style = MaterialTheme.typography.titleMedium)
}

@Composable
private fun Subheading(text: String, modifier: Modifier) {
    Text(text     = text,
         style    = MaterialTheme.typography.labelLarge,
         modifier = modifier
    )
}

// eine  praktische Extension-Function zum Formatieren einer Zahl
private fun Number.format(formatPattern: String) = formatPattern.format(CH, this)

private val CH = Locale.Builder().setLanguage("de").setRegion("CH").build()


//Todo: Kommentare entfernen sobald Country und CountryPane vorhanden sind

//@Preview(name = "Single Country Card")
//@Composable
//private fun Preview(){
//    val lummerland = Country("Lummerland", 0.12, 5)
//    CountryPane(lummerland)
//}