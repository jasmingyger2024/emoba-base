package fhnw.emoba.modules.module07.flapper.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fhnw.emoba.modules.module07.flapper.viewmodel.FlapperModel

@Composable
fun FlapperUI(viewModel: FlapperModel) {
    with(viewModel) {
        MaterialTheme {
            Scaffold(
                topBar = { Bar(title) },
                content = { padding ->
                    Body(
                        mqttBroker = mqttBroker,
                        topic = topic,
                        messagesReceived = messagesReceived,
                        messagesPublished = messagesPublished,
                        onPublish = { publish() },
                        paddingValues = padding
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(title: String) {
    TopAppBar(
        title = { Text(text = title, color = Color.White) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
    )
}

@Composable
private fun Body(
    mqttBroker: String,
    topic: String,
    messagesReceived: Int,
    messagesPublished: Int,
    onPublish: () -> Unit,
    paddingValues: PaddingValues
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(20.dp)
    ) {
        Info(mqttBroker)
        Info(topic)
        Box(Modifier.fillMaxSize()) {
            Text(
                text = messagesReceived.toString(),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(Alignment.Center)
            )
            Button(
                onClick = onPublish,
                shape = CircleShape,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text(
                    text = "Publish ($messagesPublished)",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }
}

@Composable
private fun Info(text: String){
    Text(text    = text,
        style    = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(vertical = 10.dp)
    )
}
