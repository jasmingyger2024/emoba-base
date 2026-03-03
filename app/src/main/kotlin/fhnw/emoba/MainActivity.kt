package fhnw.emoba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import fhnw.emoba.modules.module01.helloemoba.HelloEmobaApp
import fhnw.emoba.modules.module02.basics.ComposeBasicsApp
import fhnw.emoba.modules.module02.mvvm.MVVMApp
import fhnw.emoba.modules.module09.uguu.UguuApp


class MainActivity : ComponentActivity() {
    private lateinit var app: AppBase<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // WO2
        // app = MVVMApp
        app = ComposeBasicsApp

        // WO3

        setContent {
            app.Content()
        }
    }

}