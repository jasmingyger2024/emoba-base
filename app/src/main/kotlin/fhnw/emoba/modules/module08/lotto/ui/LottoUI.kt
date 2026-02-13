package fhnw.emoba.modules.module08.lotto.ui


import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import fhnw.emoba.modules.module08.lotto.data.Ziehung
import fhnw.emoba.modules.module08.lotto.viewmodel.Kaestchen
import fhnw.emoba.modules.module08.lotto.viewmodel.LottoModel

@Composable
fun LottoUI(viewModel: LottoModel) {
    MaterialTheme(colorScheme = lightColorScheme(primary = Color(0xFFCC6699))) {
        Scaffold(
            topBar = { Bar(viewModel.title) },
            content = { padding ->
                Body(
                    lottoschein = viewModel.lottoschein,
                    alleZiehungen = viewModel.alleZiehungen,
                    onNeuerLottoschein = { viewModel.neuerLottoschein(4) },
                    paddingValues = padding
                )
            },
            floatingActionButton = { FAB(neueZiehung = { viewModel.neueZiehung() }) },
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
private fun Body(
    lottoschein: List<Kaestchen>,
    alleZiehungen: List<Ziehung>,
    onNeuerLottoschein: () -> Unit,
    paddingValues: PaddingValues
) {
    //Todo: dieses Beispiel fuer ein ConstraintLayout genau anschauen
    ConstraintLayout(
        Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        val margin = 20.dp

        val (lottoscheinBox, buttonBox, ziehungenBox) = createRefs()

        LottoscheinBox(lottoschein, Modifier.constrainAs(lottoscheinBox) {
            top.linkTo(parent.top, margin)
            start.linkTo(parent.start, margin)
            end.linkTo(parent.end, margin)
            width = Dimension.fillToConstraints
        })

        ButtonBox(onNeuerLottoschein, Modifier.constrainAs(buttonBox) {
            top.linkTo(lottoscheinBox.bottom, margin)
            start.linkTo(parent.start, margin)
            end.linkTo(parent.end, margin)
            width = Dimension.fillToConstraints
        })

        ZiehungenBox(alleZiehungen, Modifier.constrainAs(ziehungenBox) {
            top.linkTo(buttonBox.bottom, margin * 3)
            start.linkTo(parent.start, margin)
            bottom.linkTo(parent.bottom, margin * 5)
            end.linkTo(parent.end, margin)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        })
    }
}

@Composable
fun ButtonBox(onNeuerLottoschein: () -> Unit, modifier: Modifier) {
    Button(
        onClick = onNeuerLottoschein,
        shape = CircleShape,
        modifier = modifier
    ) {
        Text("Neuer Lottoschein")
    }
}

@Composable
private fun LottoscheinBox(lottoschein: List<Kaestchen>, modifier: Modifier) {
    if (lottoschein.isEmpty()) {
        Box(modifier         = modifier,
            contentAlignment = Alignment.Center) {
            Text(text  = "Noch kein Lottoschein",
                style = MaterialTheme.typography.titleSmall)
        }
    } else {
        //auch eine einfache 'Row' ist scrollbar. Man braucht nicht unbedingt eine 'LazyRow'
        val scrollState = rememberScrollState()
        Row(modifier.horizontalScroll(scrollState)) {
            lottoschein.forEach {
                KaestchenBox(it)
            }
        }
    }
}

@Composable
private fun KaestchenBox(kaestchen: Kaestchen) {
    with(kaestchen) {
        Card(
            modifier = Modifier.padding(horizontal = 5.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEEEEEE)),
        ) {
            Column(Modifier.padding(5.dp)) {
                Text(text  = tip.gewinnZahlen.joinToString(separator = "  "),
                    style = MaterialTheme.typography.bodyLarge)

                Text(text  = "Superzahl ${tip.superzahl}",
                    style = MaterialTheme.typography.bodyMedium)

                Text(text     = "$richtige Richtige ${if (superzahlRichtig && richtige >=5) " mit Superzahl" else ""}",
                    style    = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }
}

@Composable
private fun ZiehungenBox(ziehungen: List<Ziehung>, modifier: Modifier) {
    if (ziehungen.isEmpty()) {
        Box(modifier         = modifier.graphicsLayer(shadowElevation = 2f),
            contentAlignment = Alignment.Center) {
            Text(text  = "Noch keine Ziehungen",
                style = MaterialTheme.typography.titleSmall)
        }
    } else {
        val scrollState = rememberScrollState()
        Column(modifier = modifier
            .shadow(1.dp)
            .padding(top = 2.dp)
            .verticalScroll(scrollState)
        ) {
            ziehungen.forEach {
                ZiehungBox(it)
                HorizontalDivider()
            }
        }

        LaunchedEffect(ziehungen.size){
            scrollState.animateScrollTo(scrollState.maxValue)
        }
    }
}


@Composable
private fun ZiehungBox(ziehung: Ziehung) {
    with(ziehung){
        ListItem(
            headlineContent  = { Text(gewinnZahlen.joinToString(separator = "  "))},
            supportingContent= { Text("Superzahl $superzahl")},
            overlineContent  = { Text("Ziehung vom ${if(Math.random() > 0.5) "Samstag" else "Mittwoch"}")},
            trailingContent  = { Icon(Icons.Filled.SentimentSatisfied, "")}
        )
    }
}

@Composable
private fun FAB(neueZiehung: () -> Unit) {
    FloatingActionButton(onClick = neueZiehung) {
        Icon(Icons.Filled.Add, "neue Ziehung")
    }
}