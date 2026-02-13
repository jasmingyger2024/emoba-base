package fhnw.emoba.modules.module08.lotto

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module08.lotto.ui.LottoUI
import fhnw.emoba.modules.module08.lotto.viewmodel.LottoModel

object LottoApp : AppBase<LottoModel>() {

    @Composable
    override fun createViewModel(activity: ComponentActivity): LottoModel = viewModel<LottoModel>()

    @Composable
    override fun AppUi(viewModel: LottoModel) {
        LottoUI(viewModel)
    }
}





