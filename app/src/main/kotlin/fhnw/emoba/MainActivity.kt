package fhnw.emoba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import fhnw.emoba.modules.module01.helloemoba.HelloEmobaApp
import fhnw.emoba.modules.module02.basics.ComposeBasicsApp
import fhnw.emoba.modules.module02.mvvm.MVVMApp
import fhnw.emoba.modules.module03.flags.FunWithFlagsApp
import fhnw.emoba.modules.module03.material.MaterialApp
import fhnw.emoba.modules.module03.sheldon.SheldonApp
import fhnw.emoba.modules.module04.beers.BeersApp
import fhnw.emoba.modules.module04.countries.CountriesApp
import fhnw.emoba.modules.module04.map.MapApp
import fhnw.emoba.modules.module04.tabs.TabsApp
import fhnw.emoba.modules.module05.cities.CitiesApp
import fhnw.emoba.modules.module05.coordinates.CoordinatesApp
import fhnw.emoba.modules.module05.multiple_screens.MultipleScreensApp
import fhnw.emoba.modules.module05.multiple_screens.ui.MultipleScreensUI
import fhnw.emoba.modules.module05.squad.SquadApp
import fhnw.emoba.modules.module07.mqtt.MqttApp
import fhnw.emoba.modules.module08.phrasomat.PhrasOMat
import fhnw.emoba.modules.module08.startrek.StarTrekApp
import fhnw.emoba.modules.module09.uguu.UguuApp
import fhnw.emoba.modules.module11.angle.AngleApp


class MainActivity : ComponentActivity() {
    private lateinit var app: AppBase<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // WO2
        // app = MVVMApp
        // app = ComposeBasicsApp

        // WO3
        // app = MaterialApp
        // app = SheldonApp
        // app = FunWithFlagsApp

        // WO4
        // app = CountriesApp
        // app = TabsApp
        // app = MapApp
        // app = BeersApp

        // WO5
        // app = MultipleScreensApp
        // app = CoordinatesApp
        // app = SquadApp
        // app = CitiesApp

        // WO7
        // app = MqttApp

        // WO8
        // app = StarTrekApp
        // app = PhrasOMat

        // WO11
        app = AngleApp

        setContent {
            app.Content()
        }
    }

}