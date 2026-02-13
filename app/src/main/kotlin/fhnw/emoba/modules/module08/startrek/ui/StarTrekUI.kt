package fhnw.emoba.modules.module08.startrek.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fhnw.emoba.R
import fhnw.emoba.modules.module08.startrek.ui.theme.StarTrekAppTheme
import fhnw.emoba.modules.module08.startrek.viewmodel.StarTrekModel

@Composable
fun StarTrekUI(viewModel: StarTrekModel) {
    StarTrekAppTheme {
        Scaffold(
            topBar = { Bar(viewModel.title) },
            content = {
                Body(
                    messageKirk = viewModel.messageKirk,
                    reponseScotty = viewModel.reponseScotty,
                    transporterStatus = viewModel.transporterStatus,
                    onTransporterStatusChange = { viewModel.updateTransporterStatus(it) },
                    paddingValues = it
                )
            }
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
    messageKirk: String,
    reponseScotty: String,
    transporterStatus: Float,
    onTransporterStatusChange: (Float) -> Unit,
    paddingValues: PaddingValues
) {
    //Todo: Column durch ConstraintLayout ersetzen
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(Color(0xFFDDFFFF))
    ) {
        Msg(messageKirk, Modifier)

        Msg(reponseScotty, Modifier)

        Panini(R.drawable.scotty, transporterStatus, reversed = false,
            modifier = Modifier)

        Panini(R.drawable.star_trek_crew, transporterStatus,
            modifier = Modifier)

        Transporter(
            transporterStatus = transporterStatus,
            onTransporterStatusChange = onTransporterStatusChange,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
private fun Msg(text: String, modifier: Modifier) {
    Text(text     = text,
         style    = MaterialTheme.typography.titleMedium,
         modifier = modifier
    )
}

@Composable
private fun Transporter(
    transporterStatus: Float,
    onTransporterStatusChange: (Float) -> Unit,
    modifier: Modifier
){
    Slider(
        value = transporterStatus,
        onValueChange = onTransporterStatusChange,
        modifier = modifier
    )
}

@Composable
private fun Panini(
    bitmap: Int,
    transporterStatus: Float,
    reversed: Boolean = true,
    modifier: Modifier
) {
    Image(painter            = painterResource(bitmap),
          contentDescription = "",
          contentScale       = ContentScale.FillHeight,
          modifier           = modifier.graphicsLayer(shape = RoundedCornerShape(corner = CornerSize(8.dp)),
                                                      clip  = true,
                                                      alpha = if (reversed) transporterStatus  else  1.0f - transporterStatus
        )
    )
}