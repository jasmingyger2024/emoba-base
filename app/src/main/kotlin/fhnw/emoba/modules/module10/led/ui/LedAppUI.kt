package fhnw.emoba.modules.module10.led.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fhnw.emoba.modules.module10.led.viewmodel.LedModel

@Composable
fun LedAppUI(viewModel: LedModel) {
    with(viewModel) {
        MaterialTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Led(
                    ledColor = color,
                    on = on,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                )

                // intentionally ugly (default Material elements compared to custom element)
                Button(
                    onClick = { changeColor() },
                ) {
                    Text("Change Color")
                }

                Switch(
                    checked = on,
                    onCheckedChange = { on = it },
                )

                Spacer(modifier = Modifier.weight(1f))

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Led(
                        ledColor = Color.Red,
                        on = true,
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                    )
                    Led(
                        ledColor = Color.Blue,
                        on = true,
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                    )
                    Led(
                        ledColor = Color.Green,
                        on = true,
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                    )
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Led(
                        ledColor = Color.Red,
                        on = false,
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                    )
                    Led(
                        ledColor = Color.Blue,
                        on = false,
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                    )
                    Led(
                        ledColor = Color.Green,
                        on = false,
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                    )
                }
            }
        }
    }

}
