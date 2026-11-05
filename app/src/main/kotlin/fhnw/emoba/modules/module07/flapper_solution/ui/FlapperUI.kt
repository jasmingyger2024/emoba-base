package fhnw.emoba.modules.module07.flapper_solution.ui

import android.content.ClipData
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import fhnw.emoba.modules.module07.flapper_solution.data.Flap
import fhnw.emoba.modules.module07.flapper_solution.ui.theme.gray300
import fhnw.emoba.modules.module07.flapper_solution.ui.theme.lightBlue600
import fhnw.emoba.modules.module07.flapper_solution.ui.theme.lightBlue900
import fhnw.emoba.modules.module07.flapper_solution.viewmodel.FlapperModel


@Composable
fun FlapperUI(viewModel: FlapperModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    with(viewModel) {
        MaterialTheme(colorScheme = lightColorScheme(primary = lightBlue900)) {
            Scaffold(topBar = { Bar(title) },
                snackbarHost = { NotificationHost(snackbarHostState) },
                content = { padding ->
                    Body(
                        mqttBroker = mqttBroker,
                        mainTopic = mainTopic,
                        allFlaps = allFlaps,
                        message = message,
                        flapsPublished = flapsPublished,
                        onMessageChange = { message = it },
                        onPublish = { publish() },
                        onCopyToClipboard = {
                            val clipData = ClipData.newPlainText("mqtt url", it)
                            clipboardManager.setPrimaryClip(clipData)
                                            },
                        paddingValues = padding
                    )
                }
            )
        }
        Notification(
            notificationMessage = notificationMessage,
            onNotificationDismissed = { notificationMessage = "" },
            snackbarHostState = snackbarHostState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(title: String) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = lightBlue600),
    )
}

@Composable
private fun Body(
    mqttBroker: String,
    mainTopic: String,
    allFlaps: List<Flap>,
    message: String,
    flapsPublished: Int,
    onMessageChange: (String) -> Unit,
    onPublish: () -> Unit,
    onCopyToClipboard: (String) -> Unit,
    paddingValues: PaddingValues
) {
    ConstraintLayout(modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize())
    {
        val (brokerInfo, topicInfo, allFlapsPanel, messageField, publishButton) = createRefs()

        Info(mqttBroker, onCopyToClipboard, Modifier.constrainAs(brokerInfo) {
            top.linkTo(parent.top, 10.dp)
            start.linkTo(parent.start, 10.dp)
        })

        Info(mainTopic, onCopyToClipboard, Modifier.constrainAs(topicInfo) {
            top.linkTo(brokerInfo.bottom, 10.dp)
            start.linkTo(parent.start, 10.dp)
        })

        AllFlapsPanel(allFlaps, Modifier.constrainAs(allFlapsPanel) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            top.linkTo(topicInfo.bottom, 15.dp)
            start.linkTo(parent.start, 10.dp)
            end.linkTo(parent.end, 10.dp)
            bottom.linkTo(messageField.top, 15.dp)
        })

        NewMessage(message, onMessageChange, Modifier.constrainAs(messageField) {
            width = Dimension.fillToConstraints
            start.linkTo(parent.start, 10.dp)
            end.linkTo(parent.end, 10.dp)
            bottom.linkTo(publishButton.top, 15.dp)
        })

        PublishButton(flapsPublished, onPublish, Modifier.constrainAs(publishButton) {
            width = Dimension.fillToConstraints
            start.linkTo(parent.start, 10.dp)
            end.linkTo(parent.end, 10.dp)
            bottom.linkTo(parent.bottom, 15.dp)
        })
    }
}

@Composable
private fun AllFlapsPanel(flaps: List<Flap>, modifier: Modifier){
    Box(modifier.border(width = 1.dp,
                        brush = SolidColor(gray300),
                        shape = RectangleShape)){
        if(flaps.isEmpty()){
            Text(text     = "Noch keine Flaps",
                 style    = MaterialTheme.typography.titleMedium,
                 modifier = Modifier.align(Alignment.Center)
            )
        } else {
            AllFlaps(flaps)
        }
    }
}

@Composable
private fun AllFlaps(flaps : List<Flap>){
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState){
        items(items = flaps){ SingleFlap(it) }
    }

    LaunchedEffect(flaps.size){
        scrollState.animateScrollToItem(flaps.size)
    }
}

@Composable
private fun SingleFlap(flap: Flap){
    with(flap){
        ListItem(
            headlineContent = { Text(message) },
            overlineContent = { Text(sender) }
        )
        HorizontalDivider()
    }
}

@Composable
private fun NotificationHost(state: SnackbarHostState) {
    SnackbarHost(state) { data ->
        Box(modifier         = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.Center) {
            Snackbar(snackbarData = data)
        }
    }
}

@Composable
private fun PublishButton(flapsPublished: Int, onPublish: () -> Unit, modifier: Modifier){
        Button(onClick  = onPublish,
               shape    = CircleShape,
               modifier = modifier
        ) {
            Text("Publish ($flapsPublished)")
        }
}

@Composable
private fun Info(text: String, onCopyToClipboard: (String) -> Unit, modifier: Modifier){
    Text(text     = text,
         style    = MaterialTheme.typography.titleSmall,
         modifier = modifier.clickable { onCopyToClipboard(text) }
    )
}

@Composable
private fun NewMessage(message: String, onMessageChange: (String) -> Unit, modifier: Modifier){
    val keyboard = LocalSoftwareKeyboardController.current
    OutlinedTextField(value           = message,
                      onValueChange   = onMessageChange,
                      modifier        = modifier,
                      keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                      keyboardActions = KeyboardActions(onDone = { keyboard?.hide() })
    )
}

@Composable
private fun Notification(notificationMessage: String, onNotificationDismissed: () -> Unit, snackbarHostState: SnackbarHostState) {
    if (notificationMessage.isNotBlank()) {
        LaunchedEffect(snackbarHostState){
            snackbarHostState.showSnackbar(
                message     = notificationMessage,
                actionLabel = "OK"
            )
            onNotificationDismissed()
        }
    }
}