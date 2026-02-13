package fhnw.emoba.modules.module03.material.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.modules.module03.material.ui.theme.MaterialAppTheme
import fhnw.emoba.modules.module03.material.viewmodel.MaterialModel
import kotlinx.coroutines.launch

@Composable
fun MaterialUI(viewModel: MaterialModel) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    with(viewModel) {
        // Oberste (erste) UI Composable fun - hier wird einmal (sic!) in der app das Theme aufgerufen.
        MaterialAppTheme(
            darkTheme,
            typoBig
        ) {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet(
                        content = { Drawer(viewModel) }
                    )
                },
                content = {
                    Scaffold(
                        topBar = { Bar(viewModel, drawerState) },
                        floatingActionButton = { FAB(viewModel) },
                        floatingActionButtonPosition = FabPosition.End,
                        content = {
                            Body(viewModel, it)
                        }
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(viewModel: MaterialModel, drawerState: DrawerState) {
    with(viewModel) {
        TopAppBar(title    = { Text(text     = title,
                                    fontSize = 20.sp //hier wird die Fontsize explizit gesetzt und wird somit nicht durch die biggerFont Einstellung beeinflusst
                                   ) },
            colors         = TopAppBarDefaults.topAppBarColors(containerColor    = MaterialTheme.colorScheme.primaryContainer,
                                                               titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer),
            navigationIcon = { DrawerIcon(drawerState) })
    }
}

@Composable
private fun DrawerIcon(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    IconButton(onClick = { scope.launch { drawerState.open() } }) {
        Icon(Icons.Filled.Menu, "Drawer öffnen")
    }
}

@Composable
private fun Body(viewModel: MaterialModel, paddingValues: PaddingValues) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.aligned(Alignment.CenterVertically),
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        Text(
            text = "Darkula",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "The choice is yours",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(20.dp))
        ThemeSwitch(viewModel)
    }
}

@Composable
private fun Drawer(viewModel: MaterialModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = "Einstellungen",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(top = 5.dp, bottom = 20.dp)
        )
        HorizontalDivider()
        Preference("Dark Theme") {
            ThemeSwitch(viewModel)
        }
        Preference("Bigger Font") {
            Checkbox(
                checked = viewModel.typoBig,
                onCheckedChange = { viewModel.toggleFontSize() })
        }
        Preference("RadioBtn") {
            RadioButton(
                selected = true,
                onClick = {})
        }
        Preference("another Btn") {
            Button(onClick = {}) {
                Text(
                    "Click Me"
                )
            }
        }
    }
}

@Composable
private fun FAB(viewModel: MaterialModel) {
    with(viewModel) {
        FloatingActionButton(onClick = { toggleTheme() })
        {
            Icon(Icons.Filled.Home, "Home")
        }
    }
}

@Composable
private fun ThemeSwitch(viewModel: MaterialModel) {
    with(viewModel) {
        Switch(
            checked = darkTheme,
            onCheckedChange = { toggleTheme() }
        )
        // DarkMode resp. Boolean die getoggelt werden via Switch oder IconToggleButton
        IconToggleButton(
            checked = darkTheme,
            onCheckedChange = { toggleTheme() }
        ) {
            Icon(
                imageVector = if (darkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                contentDescription = "ThemeToggle"
            )
        }
    }
}

@Composable
private fun Preference(label: String, control: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(label)
        control()
    }
    HorizontalDivider()
}