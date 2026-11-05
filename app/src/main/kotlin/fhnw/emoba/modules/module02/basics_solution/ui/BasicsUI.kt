package fhnw.emoba.modules.module02.basics_solution.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.modules.module02.basics_solution.viewmodel.BasicsModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicsUI(viewModel: BasicsModel) {
    with(viewModel){
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )

            Column(
                modifier = Modifier.padding(5.dp).weight(1.0F),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Heading(text = "Lorem ipsum")
                Paragraph(text = lorem)
                BasicDivider()
                BasicRow {
                    Text(text = "dolor sit")
                    Switch(checked = true, onCheckedChange = {})
                }
                BasicRow {
                    Text(text = "amet")
                    Switch(checked = booleanValue,
                        onCheckedChange = { booleanValue = !booleanValue })
                }
                BasicRow {
                    Text(text = "consectetur")
                    Checkbox(checked = booleanValue,
                        onCheckedChange = { booleanValue = !booleanValue })
                }
                BasicRow {
                    Text(text = "Textfield:")
                    TextField(value = textfieldtext,
                        onValueChange = { textfieldtext = it })
                }
                BasicDivider()
            }

            BottomAppBar {
                BasicRow {
                    Button(onClick = {}) { Text("Cancel") }
                    Button(onClick = {}) { Text("Save") }
                }
            }
        }
    }
}

@Composable
private fun Heading(text: String) {
    Text(
        text = text,
        fontSize = 42.sp
    )
}

@Composable
private fun Paragraph(text: String) {
    Text(text      = text,
         fontStyle = FontStyle.Italic,
         overflow  = TextOverflow.Ellipsis,
         maxLines  = 5)
}

@Composable
private fun BasicDivider() {
    HorizontalDivider(modifier = Modifier.padding(top    = 10.dp,
                                                  bottom = 10.dp))
}

@Composable
fun BasicRow(content: @Composable RowScope.() -> Unit) {
    Row(modifier              = Modifier.fillMaxWidth(),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        content               = content)
}
