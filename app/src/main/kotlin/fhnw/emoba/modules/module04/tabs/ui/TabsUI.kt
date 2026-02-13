package fhnw.emoba.modules.module04.tabs.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
        Text("to be replaced")

        //Todo: Kommentare entfernen
        //ContentBox(tabContent = selectedTab)
    }

}

@Composable
private fun FAB(viewModel: TabsModel) {
    //Todo: ergaenzen mit FloatingActionButton
}

//Todo: Kommentare entfernen sobald 'AvailableTabs' fertig ist

//@Composable
//private fun ContentBox(tabContent: AvailableTabs) {
//    Box(
//        modifier = Modifier.fillMaxSize().background(Color.Black),
//        contentAlignment = Alignment.Center
//    ) {
//        Image(
//            painter = painterResource(0), //Todo: ergaenzen mit richtigem Inhalt
//            contentDescription = "?", //Todo: ergaenzen mit sinnvollem Inhalt
//            modifier = Modifier.fillMaxSize().padding(30.dp)
//        )
//    }
//}