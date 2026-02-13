package fhnw.emoba.modules.module04.beers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import fhnw.emoba.modules.module04.beers.ui.theme.BeersTheme
import fhnw.emoba.modules.module04.beers.viewmodel.BeersAppModel
import kotlinx.coroutines.launch

@Composable
fun BeersAppUI(viewModel: BeersAppModel) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    BeersTheme(false) { //Todo: wie kann das umschaltbar gemacht werden?
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    content = { Drawer(viewModel) }
                )
            },
            content = {
                Scaffold(
                    topBar = { Bar(viewModel, drawerState) },
                    content = { Body(viewModel, it) },
                    bottomBar = { TabNav(viewModel) },
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(viewModel: BeersAppModel, drawerState: DrawerState) {
    with(viewModel) {
        TopAppBar(title          = { Text(title) },
                  colors         = TopAppBarDefaults.topAppBarColors(containerColor    = MaterialTheme.colorScheme.primaryContainer,
                                                                     titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer),
                  navigationIcon = { DrawerIcon(drawerState) }
        )
    }
}

@Composable
private fun DrawerIcon(drawerState: DrawerState) {
    //Todo: Schauen Sie sich genau an wie der Drawer eingeblendet wird. Wir werden
    // das noch haeufiger brauchen
    val scope = rememberCoroutineScope()
    IconButton(onClick = { scope.launch { drawerState.open() } }) {
        Icon(Icons.Filled.Menu, "Drawer öffnen")
    }
}

@Composable
private fun Body(viewModel: BeersAppModel, paddingValues: PaddingValues) {
    with(viewModel) {
        //Todo: Visualisierung aller Beers. Verwenden Sie 'LazyColumn'  und 'BeerCard'
    }
}

@Composable
private fun TabNav(viewModel: BeersAppModel) {
    with(viewModel) {
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            //Todo: vier Tabs für light, mid, dark und all Beers
        }
    }
}


@Composable
private fun Drawer(viewModel: BeersAppModel) {
    with(viewModel) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        {
            Text(
                text = "Preferences",
                modifier = Modifier.padding(6.dp),
                style = MaterialTheme.typography.titleSmall
            )
        }

        HorizontalDivider()

        //Todo: Fuegen Sie einen 'Switch' und entsprechenden 'Text' hinzu mit dem das Theme um
        // geschaltet werden kann.

        HorizontalDivider()
    }
}


//Todo: implementieren Sie die Funktion, die ein einzelnes Bier visualisiert
//@Composable
//private fun BeerCard(beer: Beer) {
//
//}



//einige Hilfsfunktionen, die Sie vielleicht brauchen koennen

@Composable
private fun Headline(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(all = 8.dp),
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
private fun BeerImage() {
    //Todo: was soll BeerImage bekommen?
   // with(beer) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black),
            horizontalArrangement = Arrangement.Center
        ) {
           // Image(painterResource(imageId), title)
        }
    //    }
}

@Composable
private fun SubTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .padding(8.dp)
    )
}

@Composable
private fun Link(url: String) {
    val uriHandler = LocalUriHandler.current
    Text(
        text = "read more...",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .padding(8.dp)
            .clickable(
                onClick = {
                    uriHandler.openUri(
                        url
                    )
                })
    )
}

@Composable
private fun Caption(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(all = 8.dp),
        maxLines = 5,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.labelSmall
    )
}