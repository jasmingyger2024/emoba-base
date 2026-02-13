package fhnw.emoba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import fhnw.emoba.modules.module01.helloemoba.HelloEmobaApp
import fhnw.emoba.modules.module09.uguu.UguuApp


class MainActivity : ComponentActivity() {
    private lateinit var app: AppBase<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // module01
          app = HelloEmobaApp

        setContent {
            app.Content()
        }
    }

}