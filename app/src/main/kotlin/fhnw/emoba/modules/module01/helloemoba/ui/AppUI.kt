package fhnw.emoba.modules.module01.helloemoba.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.modules.module01.helloemoba.viewmodel.HelloViewModel

@Composable
fun AppUI(viewModel: HelloViewModel) {
    Text(
        text = viewModel.message,
        color = Color.White,
        style = TextStyle(fontSize = 25.sp),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxSize().background(Color.Blue).padding(top = 200.dp),
    )
}
