package fhnw.emoba.modules.module09.gps.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.WrongLocation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import fhnw.emoba.R
import fhnw.emoba.modules.module09.gps.data.GeoPosition
import fhnw.emoba.modules.module09.gps.ui.theme.GpsAppTheme
import fhnw.emoba.modules.module09.gps.viewmodel.GpsModel
import java.util.Locale


private val CH = Locale.Builder().setLanguage("de").setRegion("CH").build()

@Composable
fun GpsUI(viewModel: GpsModel) {
    GpsAppTheme {
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            topBar = { Bar(viewModel.title) },
            content = { padding ->
                Body(
                    subtitle = viewModel.subtitle,
                    waypoints = viewModel.waypoints,
                    onRemoveWaypoint = { viewModel.removeWaypoint(it) },
                    onShowOnMap = { viewModel.showOnMap(it) },
                    padding = padding
                )
            },
            snackbarHost = {
                Notification(
                    notificationMessage = viewModel.notificationMessage,
                    onDismissNotification = { viewModel.notificationMessage = "" },
                    snackbarHostState = snackbarHostState
                )
            },
            floatingActionButton = {
                FAB(onRememberCurrentPosition = { viewModel.rememberCurrentPosition() })
            },
            floatingActionButtonPosition = FabPosition.Center,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(title: String) {
    TopAppBar(
        title = { Text(title, style = MaterialTheme.typography.titleSmall) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
private fun Body(
    subtitle: String,
    waypoints: List<GeoPosition>,
    onRemoveWaypoint: (GeoPosition) -> Unit,
    onShowOnMap: (GeoPosition) -> Unit,
    padding: PaddingValues
) {
    ConstraintLayout(
        Modifier
            .padding(padding)
            .fillMaxSize()
    ) {
        val margin = 20.dp

        val (messageBox, waypointsBox) = createRefs()

        MessageBox(subtitle, Modifier.constrainAs(messageBox) {
            start.linkTo(parent.start, margin)
            top.linkTo(parent.top, margin)
        })

        WaypointsBox(
            waypoints = waypoints,
            onRemoveWaypoint = onRemoveWaypoint,
            onShowOnMap = onShowOnMap,
            modifier = Modifier.constrainAs(waypointsBox) {
                start.linkTo(parent.start, margin)
                top.linkTo(messageBox.bottom, margin)
                end.linkTo(parent.end, margin)
                bottom.linkTo(parent.bottom, margin * 4)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        )
    }
}

@Composable
private fun MessageBox(text: String, modifier: Modifier) {
    Text(text = text, modifier = modifier, style = MaterialTheme.typography.titleSmall)
}

@Composable
private fun WaypointsBox(
    waypoints: List<GeoPosition>,
    onRemoveWaypoint: (GeoPosition) -> Unit,
    onShowOnMap: (GeoPosition) -> Unit,
    modifier: Modifier
) {
    if (waypoints.isEmpty()) {
        Box(
            modifier = modifier.border(
                border = BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.primary
                )
            )
        ) {
            Text(
                text = "Noch keine Wegpunkte",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(30.dp)
            )
            Image(
                painter = painterResource(R.drawable.theseus),
                contentDescription = "",
                modifier = Modifier
                    .padding(30.dp)
                    .align(Alignment.Center)
            )
        }
    } else {
        val scrollState = rememberScrollState()

        Column(
            modifier = modifier
                .border(border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary))
                .verticalScroll(scrollState)
        ) {
            waypoints.forEach {
                SingleWaypoint(
                    position = it,
                    onRemoveWaypoint = onRemoveWaypoint,
                    onShowOnMap = onShowOnMap
                )
            }
        }

        LaunchedEffect(waypoints.size) {
            scrollState.animateScrollTo(scrollState.maxValue)
        }
    }
}

@Composable
private fun SingleWaypoint(
    position: GeoPosition,
    onRemoveWaypoint: (GeoPosition) -> Unit,
    onShowOnMap: (GeoPosition) -> Unit
) {
    val density = LocalDensity.current.density

    ListItem(
        headlineContent = { Text(position.dms(), style = MaterialTheme.typography.bodyMedium) },
        supportingContent = { Text(String.format(CH, "%.1f m ü.M.", position.altitude)) },
        trailingContent = {
            IconButton(onClick = { onRemoveWaypoint(position) }) {
                Icon(
                    imageVector = Icons.Filled.WrongLocation,
                    contentDescription = "Remove",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = Modifier
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary
                ),
                shape = GenericShape { size, _ ->
                    addRect(Rect(Offset.Zero, Offset(size.width, size.height + density)))
                }
            )
            .padding(vertical = 5.dp)
            .clickable(onClick = { onShowOnMap(position) })
    )
}

@Composable
private fun FAB(onRememberCurrentPosition: () -> Unit) {
    FloatingActionButton(
        onClick = onRememberCurrentPosition,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(Icons.Filled.AddLocationAlt, "Add Location")
    }
}


@Composable
private fun Notification(
    notificationMessage: String,
    onDismissNotification: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    if (notificationMessage.isNotBlank()) {
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(
                message = notificationMessage,
                actionLabel = "OK"
            )
            onDismissNotification()
        }
    }
}
