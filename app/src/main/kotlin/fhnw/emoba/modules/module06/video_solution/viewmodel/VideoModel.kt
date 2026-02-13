package fhnw.emoba.modules.module06.video_solution.viewmodel

import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.exoplayer.ExoPlayer

/**
 *  for static url resources to play: add
 *  def media3_version = *version*
 *  implementation "androidx.media3:media3-exoplayer:$media3_version"
 *  implementation "androidx.media3:media3-ui:$media3_version"
 *
 *  for youtube keys to play: add
 *  implementation 'com.pierfrancescosoffritti.androidyoutubeplayer:core:*version*'
 */

class VideoModel : ViewModel() {
    private val staticVidURL = "https://www.michaeljob.ch/icomp/never.mp4"

    val title = "Got Vid?"

    var youtubeUrlKey by mutableStateOf("lXgkuM2NhYI") //Heros

    lateinit var exoPlayer : ExoPlayer

    fun initializeExoPlayer(activity: ComponentActivity) {
        exoPlayer = ExoPlayer.Builder(activity).build().apply {
            addMediaItem(
                MediaItem.Builder().setUri(staticVidURL)
                    .setMimeType(MimeTypes.APPLICATION_MP4)
                    .build()
            )
            prepare()
        }
    }
}