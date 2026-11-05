package fhnw.emoba.modules.module04.map.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.ramani.compose.CameraPosition


class MapModel : ViewModel() {
    val title = "MapApp powered by Maplibre"
    val brugg = LatLng(latitude = 47.48149026165488, longitude = 8.211623564471637)
    val basel = LatLng(latitude = 47.56, longitude = 7.57)

    var mapStyle: Style.Builder

    init {
        //Map Style (Tiles) from openfreemap.org -> bildet die Karte ab
        val styleAttr: Style.Builder = Style.Builder().fromUri("https://tiles.openfreemap.org/styles/liberty")
        mapStyle = styleAttr
    }

    var mapCameraPosition by mutableStateOf(
        CameraPosition(
            target = basel,
            zoom = 8.0,
            tilt = 0.0,
            bearing = 0.0,
        )
    )

    fun resetMapToBrugg(){
        //Todo: zoom in and center to show Brugg Campus
        mapCameraPosition = CameraPosition(
            target = brugg,
            zoom = 15.0,
            tilt = 0.0,
            bearing = 0.0,
        )
    }
}
