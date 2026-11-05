package fhnw.emoba.modules.module04.tabs.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import fhnw.emoba.modules.module04.tabs.viewmodel.TabId
import fhnw.emoba.modules.module04.tabs.viewmodel.TabsModel

@Composable
fun TabsUI(viewModel: TabsModel) {
    MaterialTheme {
        Scaffold(topBar                       = { Bar(viewModel) },
                 floatingActionButton         = { FAB(viewModel) },
                 floatingActionButtonPosition = FabPosition.End,
            content = { padding ->
                Body(viewModel, padding)
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(viewModel: TabsModel) {
    with(viewModel) {
        TopAppBar(title = { Text(title) },
                 colors = TopAppBarDefaults.topAppBarColors(containerColor    = MaterialTheme.colorScheme.primaryContainer,
                                                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer))
    }
}

@Composable
private fun Body(viewModel: TabsModel, padding: PaddingValues) {
    with(viewModel){
        //Todo: mit PrimaryTabRow und Tab ersetzen

        Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
        ) {
            PrimaryTabRow(selectedTabIndex = selectedTab.ordinal) {
                for (tab in TabId.entries) {
                    Tab(
                        text = { Text(tab.title)},
                        selected = tab == selectedTab,
                        onClick = { selectedTab =  tab },

                    )
                }
            }

            ContentBox(tabContent = selectedTab)

        }
    }
}

@Composable
private fun FAB(viewModel: TabsModel) {
    //Todo: ergaenzen mit FloatingActionButton
    FloatingActionButton(
        onClick = {viewModel.selectedTab = TabId.FIRST},
    ) {
        Icon(Icons.Filled.Home, "Home")
    }
}

@Composable
private fun ContentBox(tabContent: TabId) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(tabContent.imageDrawble), //Todo: ergaenzen mit richtigem Inhalt
            contentDescription = tabContent.title, //Todo: ergaenzen mit sinnvollem Inhalt
            modifier = Modifier.fillMaxSize().padding(30.dp)
        )
    }
}