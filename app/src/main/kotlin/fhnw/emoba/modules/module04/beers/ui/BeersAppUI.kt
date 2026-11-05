package fhnw.emoba.modules.module04.beers.ui

import android.graphics.Paint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import fhnw.emoba.modules.module04.beers.data.Beer
import fhnw.emoba.modules.module04.beers.ui.theme.BeersTheme
import fhnw.emoba.modules.module04.beers.viewmodel.BeersAppModel
import fhnw.emoba.modules.module04.beers.viewmodel.TabId
import fhnw.emoba.modules.module04.countries.ui.CountryPane
import kotlinx.coroutines.launch

@Composable
fun BeersAppUI(viewModel: BeersAppModel) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    BeersTheme(viewModel.darkTheme) { //Todo: wie kann das umschaltbar gemacht werden? - DONE
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
    // das noch haeufiger brauchen - DONE
    val scope = rememberCoroutineScope()
    IconButton(onClick = { scope.launch { drawerState.open() } }) {
        Icon(Icons.Filled.Menu, "Drawer öffnen")
    }
}

@Composable
private fun Body(viewModel: BeersAppModel, paddingValues: PaddingValues) {
    with(viewModel) {
        //Todo: Visualisierung aller Beers. Verwenden Sie 'LazyColumn'  und 'BeerCard'
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            items(viewModel.filteredBeers, key = {it.title}) {
                BeerCard(beer = it)
            }
        }

    }
}

@Composable
private fun TabNav(viewModel: BeersAppModel) {
    with(viewModel) {
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            //Todo: vier Tabs für light, mid, dark und all Beers - DONE
            PrimaryTabRow(selectedTabIndex = selectedTab.ordinal) {
                for (tab in TabId.entries) {
                    Tab(
                        text = { Text(tab.title)},
                        selected = tab == selectedTab,
                        onClick = { selectedTab =  tab },
                        )
                }
            }
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
        // geschaltet werden kann. - DONE
        Row(
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Dark/ Light Theme")

            with(viewModel) {
                Switch(
                    checked = darkTheme,
                    onCheckedChange = { toggleTheme() }
                )
            }
        }
        HorizontalDivider()
    }
}


//Todo: implementieren Sie die Funktion, die ein einzelnes Bier visualisiert
@Composable
private fun BeerCard(beer: Beer) {
    Card(
        modifier = Modifier.padding(15.dp)
    ) {
        BeerImage(beer, beer.imageId, beer.title)
        Headline(beer.title)
        SubTitle("Vote: " + beer.voteAverage + "/10")
        Caption(beer.overview)
        Link(beer.link)
    }
}



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
private fun BeerImage(beer: Beer, imageId: Int, title : String) {
    //Todo: was soll BeerImage bekommen? - DONE
   with(beer) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black),
            horizontalArrangement = Arrangement.Center
        ) {
           Image(painterResource(imageId), title)
        }
    }
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