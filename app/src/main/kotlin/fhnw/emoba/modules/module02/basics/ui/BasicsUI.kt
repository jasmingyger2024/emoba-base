package fhnw.emoba.modules.module02.basics.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import fhnw.emoba.modules.module02.basics.viewmodel.BasicsModel

@Composable
fun BasicsUI(viewModel: BasicsModel) {
    Row {
        Text(text     = viewModel.title,
             fontSize = 28.sp)
    }
}
