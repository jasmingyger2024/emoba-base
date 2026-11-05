package fhnw.emoba.modules.module04.beers_solution.ui

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fhnw.emoba.R
import fhnw.emoba.modules.module04.beers_solution.data.Beer
import fhnw.emoba.modules.module04.beers_solution.ui.theme.BeersTheme
import fhnw.emoba.modules.module04.beers_solution.viewmodel.BeersAppModel
import fhnw.emoba.modules.module04.beers_solution.viewmodel.TabId
import kotlinx.coroutines.launch

@Composable
fun BeersAppUI(viewModel: BeersAppModel) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    BeersTheme(viewModel.darkTheme) { //Todo: done
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
        TopAppBar(title = { Text(title) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
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
        if (beers.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                state = lazyliststate, //to reset scroll state if new data gets selected in viewModel
                modifier = Modifier.padding(paddingValues)
            ) {
                items(items = beers, key = { it.imageId }) { BeerCard(it) }
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
            PrimaryTabRow(selectedTabIndex = selectedTab.ordinal) {  //Todo: done
                for (tab in TabId.entries) {
                    Tab(text = { Text(tab.beercolor) },
                        selected = tab == selectedTab,
                        onClick = {
                            selectedTab = tab
                            loadData()
                        }
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable(onClick = { toggleTheme() }),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Dark Theme")
            Switch(checked = darkTheme,
                onCheckedChange = { toggleTheme() })
        }
        HorizontalDivider()
    }
}

@Composable
private fun BeerCard(beer: Beer) {
    with(beer) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)

        ) {
            Column {
                BeerImage(beer)
                Headline(title)
                SubTitle(text = "Vote: $voteAverage/10")
                Caption(overview)
                Link(url = link)
            }
        }
    }
}

@Composable
private fun Headline(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(all = 8.dp),
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
private fun BeerImage(beer: Beer) {
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


@Preview
@Composable
private fun BeerCardPreviewDark() = PreviewBeerCard(true)

@Preview
@Composable
private fun BeerCardPreviewLight() = PreviewBeerCard(false)

@Composable
private fun PreviewBeerCard(dark: Boolean) {
    val beer = Beer(
        title = "Dark Side",
        link = "https://bathales.com/",
        overview = "A perfectly smooth stout.",
        voteAverage = 7.4,
        beercolor = "light",
        imageId = R.drawable.english_style_sweet_stout_milk_stout
    )
    BeersTheme(dark) {
        BeerCard(beer)
    }
}