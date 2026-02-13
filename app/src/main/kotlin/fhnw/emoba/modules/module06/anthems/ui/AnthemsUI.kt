package fhnw.emoba.modules.module06.anthems.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import fhnw.emoba.modules.module06.anthems.viewmodel.AnthemsModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnthemsUI(viewModel: AnthemsModel) {
    val uriHandler = LocalUriHandler.current
    val linkColor = remember { Color(0xFF190EA4) }
    val keyboard = LocalSoftwareKeyboardController.current
    
    MaterialTheme {
        Scaffold(topBar = { TopAppBar(title  = { Text("Fun with Anthems") },
                                      colors = TopAppBarDefaults.topAppBarColors(containerColor    = MaterialTheme.colorScheme.primaryContainer,
                                                                                 titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer)) })
        {
            with(viewModel) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                       modifier            = Modifier.fillMaxSize()
                                                     .padding(it)
                                                     .padding(20.dp))
                {
                    Text("countrycode.org",
                         style = MaterialTheme.typography.bodyLarge,
                         color = linkColor,
                         modifier = Modifier.clickable(onClick = { uriHandler.openUri("https://countrycode.org") }))
                    Spacer(modifier = Modifier.height(20.dp))
                    OutlinedTextField(
                            value         = countryCode,
                            onValueChange = { countryCode = it },
        
                            placeholder = {Text("ISO 3 Country Code")},
                            modifier = Modifier.fillMaxWidth(),
                            trailingIcon = {
                                IconButton(onClick = { countryCode = "" }) {
                                    Icon(Icons.Filled.Clear, "Clear") }
                            },
                            keyboardOptions = KeyboardOptions(
                                autoCorrectEnabled = false,
                                keyboardType = KeyboardType.Ascii,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(onDone = {
                                keyboard?.hide()
                                viewModel.startPlayer()
                            }))
                    Spacer(modifier = Modifier.height(40.dp))
                    val enabled = countryCode.length == 3
                    if(playerIsReady){
                        IconButton(onClick = { startPlayer() },
                                   modifier = Modifier.background(SolidColor(Color.LightGray),
                                                                  shape = CircleShape,
                                                                  alpha = if(enabled) 1.0f else 0.3f)
                                       .size(72.dp),
                                   enabled = countryCode.length == 3) {
                            Icon(Icons.Filled.PlayArrow, "",
                                 tint = if(enabled) Color.Black else Color.LightGray,
                                 modifier = Modifier.size(48.dp))
                        }
                    }
                    else {
                        IconButton(onClick = { pausePlayer() },
                                   modifier = Modifier.background(Color.LightGray, shape = CircleShape).size(72.dp)) {
                            Icon(Icons.Filled.Pause, "",
                                 modifier = Modifier.size(48.dp))
                        }
                    }
                }
            }
        }
    }
}