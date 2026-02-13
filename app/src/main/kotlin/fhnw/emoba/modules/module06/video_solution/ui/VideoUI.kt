package fhnw.emoba.modules.module06.video_solution.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import fhnw.emoba.modules.module06.video_solution.viewmodel.VideoModel


@Composable
fun VideoUI(viewModel: VideoModel) {
    MaterialTheme {
        Scaffold(
            topBar = { Bar(viewModel.title) },
            content = { padding ->
                Body(viewModel.youtubeUrlKey, viewModel.exoPlayer, padding)
            }
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
private fun Body(youtubeUrlKey: String, exoPlayer: ExoPlayer, paddingValues: PaddingValues) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(20.dp)
    ) {
        VSpace(20)
        YoutubePlayer(youtubeUrlKey)
        VSpace(40)
        VideoPlayer(exoPlayer)
    }
}

@Composable
fun VideoPlayer(exoPlayer: ExoPlayer) {
    val context = LocalContext.current

    // Gateway to traditional Android Views
    AndroidView(
        factory = {
            PlayerView(context).apply { player = exoPlayer }
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        update = {
            // The callback to be invoked after the layout is inflated.
            // exoPlayer.play() // if autoplay shall happen for example
        }
    )
}


@Composable
fun YoutubePlayer(key: String) {
    val activityLifecycle = LocalLifecycleOwner.current.lifecycle
    val context = LocalContext.current

    val youtubePlayer = remember(key) {
        YouTubePlayerView(context).apply {
            activityLifecycle.addObserver(this)
            enableAutomaticInitialization = false
            initialize(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.cueVideo(key, 0f)
                }
            })
        }
    }

    AndroidView(
        factory = { youtubePlayer },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        update = {}
    )
}

@Composable
private fun VSpace(height: Int) {
    Spacer(modifier = Modifier.height(height.dp))
}