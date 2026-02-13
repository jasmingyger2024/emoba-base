package fhnw.emoba.modules.module01.helloemoba

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.AppBase
import fhnw.emoba.modules.module01.helloemoba.ui.AppUI
import fhnw.emoba.modules.module01.helloemoba.viewmodel.HelloViewModel

/**
 * Von der App darf es nur eine Instanz geben.
 *
 * Das wird von Kotlin direkt unterstuetzt. Mit 'object'
 *
 * Alle unsere Apps implementieren die abstrakte Klasse 'AppBase'
 * damit sie in der 'MainActivity' verwendet werden koennen.
 *
 */
object HelloEmobaApp : AppBase<HelloViewModel>() {

	@Composable
	override fun createViewModel(activity: ComponentActivity): HelloViewModel = viewModel<HelloViewModel>()

    @Composable
    override fun AppUi(viewModel: HelloViewModel) {
        AppUI(viewModel)
    }

}

