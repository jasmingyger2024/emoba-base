package fhnw.emoba.modules.module03.sheldon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource

@Composable
fun SheldonUI(viewModel: SheldonViewModel) {

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(viewModel.image),
            contentDescription = "Sheldon holding a flag",
            modifier = Modifier.align(Alignment.Center) // dieser modifier funktioniert nur wenn das image in einer Box, Column oder einer Row ist!
        )
    }
}