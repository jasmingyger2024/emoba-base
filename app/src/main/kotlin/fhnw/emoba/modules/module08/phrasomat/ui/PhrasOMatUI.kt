package fhnw.emoba.modules.module08.phrasomat.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import fhnw.emoba.modules.module08.phrasomat.viewmodel.PhrasOMatModel

@Composable
fun PhrasOMatUI(viewModel: PhrasOMatModel) {
    MaterialTheme(colorScheme = lightColorScheme(primary = Color(0xFFCC6699))) {
        Scaffold(
            topBar = { Bar(viewModel.title) },
            content = { Body(it) },
            floatingActionButton = { FAB() },
            floatingActionButtonPosition = FabPosition.Center,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(title: String) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
private fun Body(paddingValues: PaddingValues) {
    ConstraintLayout(Modifier
        .padding(paddingValues)
        .fillMaxSize()) {
        val margin = 20.dp

        val (phrasesBox) = createRefs()

        //Todo 1: emptyList ersetzen
        PhrasesBox(
            phrases = emptyList(),
            modifier = Modifier.constrainAs(phrasesBox) {
                start.linkTo(parent.start, margin)
                top.linkTo(parent.top, margin)
                end.linkTo(parent.end, margin)
                bottom.linkTo(parent.bottom, margin * 4)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        )
    }
}

@Composable
private fun PhrasesBox(phrases: List<String>, modifier: Modifier) {
    if (phrases.isEmpty()) {
        Box(modifier         = modifier.shadow(2.dp),
            contentAlignment = Alignment.Center) {
            Text(text  = "Noch keine Phrasen",
                 style = MaterialTheme.typography.titleSmall)
        }
    }
    else {
        // Todo 1: LazyColumn mit allen bisher erzeugten Phrasen
        // SinglePhrase benutzen
    }
}

@Composable
private fun SinglePhrase(phrase: String) {
    Box(
        modifier = Modifier
            .height(80.dp)
            .padding(vertical = 5.dp, horizontal = 10.dp)
    ) {
        Text(phrase, Modifier.align(Alignment.CenterStart))
    }
    HorizontalDivider()
}

@Composable
private fun FAB() {
    FloatingActionButton(onClick = {  })  //Todo: neue Phrase generieren lassen
       { Icon(Icons.Filled.Add, "neue Phrase") }
}