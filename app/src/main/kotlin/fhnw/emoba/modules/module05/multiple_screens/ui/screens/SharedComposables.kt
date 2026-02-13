package fhnw.emoba.modules.module05.multiple_screens.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fhnw.emoba.modules.module05.multiple_screens.viewmodel.MultipleScreensModel
import fhnw.emoba.modules.module05.multiple_screens.viewmodel.ScreenId
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    viewModel: MultipleScreensModel,
    screenId: ScreenId,
    lastScreenId: ScreenId? = null,
    nextScreenId: ScreenId? = null
) {
    with(viewModel) {
        TopAppBar(
            title = { Text(screenId.title) },
            navigationIcon = {
                if (lastScreenId != null) {
                    BackToScreenIcon(this, lastScreenId)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHighest),
            actions = {
                if (nextScreenId != null) {
                    IconButton(onClick = { }) {  //Todo auf nächsten Screen wechseln
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, "ArrowForward")
                    }
                }
            }
        )
    }
}

@Composable
fun DefaultScreen(
    viewModel: MultipleScreensModel,
    screenId: ScreenId,
    lastScreenId: ScreenId? = null,
    nextScreenId: ScreenId? = null
) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                viewModel = viewModel,
                screenId = screenId,
                lastScreenId = lastScreenId,
                nextScreenId = nextScreenId
            )
        },
        floatingActionButton = { GoHomeFAB(viewModel) },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        DefaultBody(screenId, it)
    }
}

@Composable
fun DrawerIcon(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    IconButton(onClick = { scope.launch { drawerState.open() } }) {
        Icon(Icons.Filled.Menu, "Menu")
    }
}

@Composable
fun BackToScreenIcon(viewModel: MultipleScreensModel, screenId: ScreenId) {
    with(viewModel) {
        IconButton(onClick = { }) {  //Todo: auf vorgängigen Screen wechseln
            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
        }
    }
}

@Composable
fun DefaultBody(screenId: ScreenId, paddingValues: PaddingValues) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(paddingValues)
            .padding(15.dp)
            .fillMaxSize()
    )
    {
        Image(
            painter = painterResource(screenId.resId),
            contentDescription = screenId.title,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(20.dp))
        )
    }
}

@Composable
fun GoHomeFAB(viewModel: MultipleScreensModel) {
    with(viewModel) {
        FloatingActionButton(
            onClick = {}  //Todo: auf Home Screen wechseln
        ) { Icon(Icons.Filled.Home, "Home") }
    }
}

@Composable
fun Drawer(viewModel: MultipleScreensModel) {
    Column {
        DrawerRow(viewModel, ScreenId.HOME)
        DrawerRow(viewModel, ScreenId.YEAR_1955)
        DrawerRow(viewModel, ScreenId.YEAR_2015)
        DrawerRow(viewModel, ScreenId.ABOUT)
    }
}

@Composable
fun DrawerRow(viewModel: MultipleScreensModel, screenId: ScreenId) {
    with(viewModel) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = screenId.resId),
                contentDescription = screenId.title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
            Text(
                text = screenId.title,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .clickable(onClick = { }) //Todo navigate to screens
            )
        }
        HorizontalDivider()
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun DrawerPreview() {
    Drawer(viewModel = MultipleScreensModel())
}