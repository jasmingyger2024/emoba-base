package fhnw.emoba.modules.module02.basics.ui

import android.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.modules.module02.basics.viewmodel.BasicsModel

import fhnw.emoba.modules.module02.mvvm.viewmodel.MVVMModel

@Composable
fun BasicsUI(viewModel: BasicsModel) {
    Scaffold(topBar = { Bar(viewModel) },
            content = { Body(viewModel, it)},
            bottomBar = {BottomBar(viewModel)})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(viewModel: BasicsModel) {
    TopAppBar(title  = { Text("${viewModel.title} ") },
        colors = TopAppBarDefaults.topAppBarColors(containerColor    = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
private fun Body(viewModel: BasicsModel, paddingValues: PaddingValues) {
    with(viewModel) {
        Column(
            modifier = Modifier.padding(paddingValues)
                .padding(
                    top = 50.dp,
                    bottom = 50.dp,
                    start = 20.dp,
                    end = 20.dp
                )
                .fillMaxSize()

        ) {
            Text(
                text = "Lorem Ipsum",
                fontSize = 40.sp,
                modifier = Modifier.padding(bottom = 20.dp),
            )

            Text(
                text = lorem,
                fontSize = 14.sp,
                maxLines = 5,
                fontStyle = FontStyle.Italic
            )

            HorizontalDivider(
                modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "dolor sit",
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = doloris,
                    onCheckedChange = {doloris = it}
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "amet",
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = amen,
                    onCheckedChange = {amen = it}
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "consectetur",
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Checkbox(
                    checked = consequence,
                    onCheckedChange = {consequence = it}
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Textfield",
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                TextField(
                    value = textfieldtext,
                    onValueChange = {textfieldtext = it}
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
            )

        }
    }
}



@Composable
private fun BottomBar(viewModel: BasicsModel) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    )
        {
        Button(
            onClick = {  },
            modifier = Modifier.padding(start = 10.dp)
            ) {
            Text(text = "Cancel")
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = {  },
            modifier = Modifier.padding(end = 10.dp)
            ) {
            Text(text = "Save")
        }
    }
}
