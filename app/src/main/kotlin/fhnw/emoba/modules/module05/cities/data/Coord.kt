package fhnw.emoba.modules.module05.cities.data

import org.json.JSONObject

data class Coord(val jsonObject: JSONObject) {
    val lon: Double = jsonObject.getDouble("lon")
    val lat: Double = jsonObject.getDouble("lat")
}
