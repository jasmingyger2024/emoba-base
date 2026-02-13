package fhnw.emoba.modules.module06.video_solution

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module06.video_solution.ui.VideoUI
import fhnw.emoba.modules.module06.video_solution.viewmodel.VideoModel

object VideoAppSolution : AppBase<VideoModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): VideoModel {
        val viewModel = viewModel<VideoModel>()
        viewModel.initializeExoPlayer(activity)
        return viewModel
    }

    @Composable
    override fun AppUi(viewModel: VideoModel) {
        VideoUI(viewModel)
    }
}