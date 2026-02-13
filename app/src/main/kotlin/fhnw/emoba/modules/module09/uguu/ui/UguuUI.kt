package fhnw.emoba.modules.module09.uguu.ui

import android.content.ClipData
import android.graphics.Bitmap
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import fhnw.emoba.modules.module09.uguu.viewmodel.HomeScreenTabId
import fhnw.emoba.modules.module09.uguu.viewmodel.UguuModel

@Composable
fun UguuUI(viewModel: UguuModel) {
    MaterialTheme(colorScheme = lightColorScheme(primary = Color(0xFF646DCC))) {
        Scaffold(
            topBar = { Bar(viewModel.title) },
            content = { padding ->
                Body(
                    currentTab = viewModel.currentTab,
                    onTabClick = { viewModel.currentTab = it },
                    originalImg = viewModel.originalImg,
                    uploadInProgress = viewModel.uploadInProgress,
                    uguuURL = viewModel.uguuURL,
                    onCopyURL = {
                        val clipData = ClipData.newPlainText("uguu url", it)
                        viewModel.clipboardManager.setPrimaryClip(clipData)
                    },
                    downloadedImg = viewModel.downloadedImg,
                    downloadMessage = viewModel.downloadMessage,
                    downloadInProgress = viewModel.downloadInProgress,
                    padding = padding
                )
            },
            floatingActionButton = {
                FAB(
                    currentTab = viewModel.currentTab,
                    uguuURL = viewModel.uguuURL,
                    onUpload = { viewModel.uploadToUguu() },
                    onDownload = { viewModel.downloadFromUguu() }
                )
            },
            floatingActionButtonPosition = FabPosition.Center,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(title: String) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
fun Body(
    currentTab: HomeScreenTabId,
    onTabClick: (HomeScreenTabId) -> Unit,
    originalImg: Bitmap,
    uploadInProgress: Boolean,
    uguuURL: String?,
    onCopyURL: (String) -> Unit,
    downloadedImg: Bitmap?,
    downloadMessage: String,
    downloadInProgress: Boolean,
    padding: PaddingValues
) {
    Column(modifier = Modifier.padding(padding))
    {
        PrimaryTabRow(selectedTabIndex = currentTab.ordinal) {
            for (tab in HomeScreenTabId.entries) {
                Tab(text = { Text(tab.title) },
                    selected = tab == currentTab,
                    onClick = { onTabClick(tab) }
                )
            }
        }
        Crossfade(
            targetState = currentTab,
            animationSpec = tween(500), // auch die Uebergaenge zwischen den Tabs kann man animieren
            label = currentTab.title
        ) { tab ->
            when (tab) {
                HomeScreenTabId.UPLOAD -> {
                    UploadTab(
                        originalImg = originalImg,
                        uploadInProgress = uploadInProgress,
                        uguuURL = uguuURL,
                        onCopyURL = onCopyURL
                    )
                }
                HomeScreenTabId.DOWNLOAD -> {
                    DownloadTab(
                        downloadedImg = downloadedImg,
                        downloadMessage = downloadMessage,
                        downloadInProgress = downloadInProgress,
                        uguuURL = uguuURL,
                        onCopyURL = onCopyURL
                    )
                }
            }
        }
    }
}

@Composable
fun UploadTab(
    originalImg: Bitmap,
    uploadInProgress: Boolean,
    uguuURL: String?,
    onCopyURL: (String) -> Unit
) {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (picture, uguu) = createRefs()

        val margin = 20.dp

        CrewPicture(originalImg, Modifier.constrainAs(picture) {
            start.linkTo(parent.start, margin)
            top.linkTo(parent.top,  margin )
            end.linkTo(parent.end, margin)
            width = Dimension.fillToConstraints
        })

        UploadBox(
            uploadInProgress = uploadInProgress,
            uguuURL = uguuURL,
            onCopyURL = onCopyURL,
            modifier = Modifier.constrainAs(uguu){
                top.linkTo(picture.bottom, margin)
                start.linkTo(parent.start, margin * 2)
                end.linkTo(parent.end, margin * 2)
                width = Dimension.fillToConstraints
            }
        )
    }
}

@Composable
private fun UploadBox(
    uploadInProgress: Boolean,
    uguuURL: String?,
    onCopyURL: (String) -> Unit,
    modifier: Modifier
) {
    when {
        uploadInProgress -> {
            LinearProgressIndicator(modifier)
        }
        uguuURL != null -> {
            CenteredText(uguuURL, onCopyURL, modifier)
        }
        else -> {
            CenteredText("Not uploaded to uguu.se", onCopyURL, modifier)
        }
    }
}


@Composable
private fun DownloadTab(
    downloadedImg: Bitmap?,
    downloadMessage: String,
    downloadInProgress: Boolean,
    uguuURL: String?,
    onCopyURL: (String) -> Unit
) {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (response, uguu) = createRefs()
        val margin = 20.dp

        ResponseBox(
            downloadedImg = downloadedImg,
            downloadMessage = downloadMessage,
            uguuURL = uguuURL,
            onCopyURL = onCopyURL,
            modifier = Modifier.constrainAs(response){
                start.linkTo(parent.start, margin)
                top.linkTo(parent.top,  margin )
                end.linkTo(parent.end, margin)
                bottom.linkTo(parent.bottom, margin * 6)
                width = Dimension.fillToConstraints
            }
        )

        DownloadBox(
            downloadInProgress = downloadInProgress,
            uguuURL = uguuURL,
            onCopyURL = onCopyURL,
            modifier = Modifier.constrainAs(uguu){
                bottom.linkTo(parent.bottom, margin * 5)
                start.linkTo(parent.start, margin * 2)
                end.linkTo(parent.end, margin * 2)
                width = Dimension.fillToConstraints
            }
        )
    }
}

@Composable
private fun ResponseBox(
    downloadedImg: Bitmap?,
    downloadMessage: String,
    uguuURL: String?,
    onCopyURL: (String) -> Unit,
    modifier: Modifier
) {
    if (downloadedImg != null) {
        CrewPicture(downloadedImg, modifier)
    } else {
        Box(modifier, contentAlignment = Alignment.Center) {
            when {
                downloadMessage.isNotBlank() -> {
                    CenteredText(downloadMessage, onCopyURL)
                }
                uguuURL != null -> {
                    CenteredText("Not downloaded", onCopyURL)
                }
                else -> {
                    CenteredText("No URL for download", onCopyURL)
                }
            }
        }
    }
}

@Composable
private fun DownloadBox(
    downloadInProgress: Boolean,
    uguuURL: String?,
    onCopyURL: (String) -> Unit,
    modifier: Modifier
) {
    when {
        downloadInProgress -> {
            LinearProgressIndicator(modifier = modifier)
        }
        uguuURL != null -> {
            CenteredText(uguuURL, onCopyURL, modifier = modifier)
        }
    }
}

@Composable
private fun FAB(
    currentTab: HomeScreenTabId,
    uguuURL: String?,
    onUpload: () -> Unit,
    onDownload: () -> Unit
) {
    when {
        currentTab == HomeScreenTabId.UPLOAD -> FloatingActionButton(
            onClick = onUpload,
            containerColor = MaterialTheme.colorScheme.primary
        ) { Icon(Icons.Filled.CloudUpload, "Upload") }

        uguuURL != null -> FloatingActionButton(
            onClick = onDownload,
            containerColor = MaterialTheme.colorScheme.secondary
        ) { Icon(Icons.Filled.CloudDownload, "Download") }
    }
}

@Composable
private fun CrewPicture(bitmap: Bitmap, modifier: Modifier){
    Image(bitmap             = bitmap.asImageBitmap(),
          contentDescription = "",
          contentScale       = ContentScale.FillWidth,
          modifier           = modifier.graphicsLayer(shape = RoundedCornerShape(corner = CornerSize(8.dp)),
                                                      clip  = true))
}

@Composable
private fun CenteredText(text: String, onCopyURL: (String) -> Unit, modifier: Modifier = Modifier) {
    if (text.contains("http")) {
        Text(text = text,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Center,
            modifier = modifier.clickable { onCopyURL(text) })
    } else {
        Text(text = text,
            textAlign = TextAlign.Center,
            modifier = modifier)
    }
}