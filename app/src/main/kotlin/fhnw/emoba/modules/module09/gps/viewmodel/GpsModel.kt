package fhnw.emoba.modules.module09.gps.viewmodel

import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.AndroidUriHandler
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fhnw.emoba.modules.module09.gps.data.GPSConnector
import fhnw.emoba.modules.module09.gps.data.GeoPosition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GpsModel : ViewModel() {
    val title        = "'Theseus der Klugscheisser' App"
    val subtitle     = "Faden ist sooo 798 v.Chr., Ariadne!"
    val waypoints = mutableStateListOf<GeoPosition>()
    var notificationMessage by mutableStateOf("")

    private lateinit var uriHandler : AndroidUriHandler

    private lateinit var locator: GPSConnector

    fun updateLocator(locator:  GPSConnector) {
        this.locator = locator
    }

    fun updateUriHandler(activity: ComponentActivity){
        uriHandler = AndroidUriHandler(activity)
    }

    fun rememberCurrentPosition(){
        viewModelScope.launch(Dispatchers.IO) {
            locator.getLocation(onNewLocation      = { waypoints.add(it)
                                                       notificationMessage = "neuer Wegpunkt"
                                                     },
                                onFailure          = {}, //Todo: was machen wir?
                                onPermissionDenied = { notificationMessage = "Keine Berechtigung." },  //Todo: was machen wir?
            )
        }
    }

    fun removeWaypoint(position: GeoPosition) = waypoints.remove(position)

    fun showOnMap(position: GeoPosition) = uriHandler.openUri(position.asGoogleMapsURL())

}