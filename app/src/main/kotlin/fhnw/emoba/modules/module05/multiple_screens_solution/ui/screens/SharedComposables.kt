package fhnw.emoba.modules.module05.multiple_screens_solution.ui.screens

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
import fhnw.emoba.modules.module05.multiple_screens_solution.viewmodel.ScreenId
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    screenId: ScreenId,
    lastScreenId: ScreenId? = null,
    nextScreenId: ScreenId? = null,
    navigateToLastScreen: () -> Unit,
    navigateToNextScreen: () -> Unit
) {
        TopAppBar(
            title = { Text(screenId.title) },
            navigationIcon = {
                if (lastScreenId != null) {
                    BackToScreenIcon(onBackClicked = { navigateToLastScreen() })
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHighest),
            actions = {
                if (nextScreenId != null) {
                    IconButton(
                        onClick = { navigateToNextScreen() }
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, "ArrowForward")
                    }
                }
            }
        )
}

@Composable
fun DefaultScreen(
    screenId: ScreenId,
    lastScreenId: ScreenId? = null,
    nextScreenId: ScreenId? = null,
    navigateToHome: () -> Unit,
    navigateToLastScreen: () -> Unit = {},
    navigateToNextScreen: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                screenId = screenId,
                lastScreenId = lastScreenId,
                nextScreenId = nextScreenId,
                navigateToLastScreen = navigateToLastScreen,
                navigateToNextScreen = navigateToNextScreen
            )
        },
        floatingActionButton = { GoHomeFAB(navigateToHome) },
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
fun BackToScreenIcon(onBackClicked: () -> Unit) {
    IconButton(onClick = onBackClicked) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
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
    ) {
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
fun GoHomeFAB(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Filled.Home, "Home")
    }
}

@Composable
fun Drawer(onScreenClick: (ScreenId) -> Unit) {
    Column {
        DrawerRow(ScreenId.HOME, onScreenClick)
        DrawerRow(ScreenId.YEAR_1955, onScreenClick)
        DrawerRow(ScreenId.YEAR_2015, onScreenClick)
        DrawerRow(ScreenId.ABOUT, onScreenClick)
    }
}

@Composable
fun DrawerRow(screenId: ScreenId, onClick: (ScreenId) -> Unit) {
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
            Text(text = screenId.title,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .clickable(onClick = { onClick(screenId) })
            )
        }
        HorizontalDivider()
}

@Preview
@Composable
private fun DrawerPreview() {
    Drawer(onScreenClick = {})
}

@Preview
@Composable
private fun DefaultBodyPreview() {
    DefaultBody(ScreenId.HOME, PaddingValues(0.dp))
}