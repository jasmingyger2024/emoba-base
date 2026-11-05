package fhnw.emoba.modules.module05.cities.data

import fhnw.emoba.modules.module05.squad.data.Squad
import org.json.JSONObject

data class City(val jsonObject: JSONObject) {
    val id: Int       = jsonObject.getInt("id")
    val name: String   = jsonObject.getString("name")
    val state: String  = jsonObject.getString("state")
    val country: String= jsonObject.getString("country")
    val coord: Coord   = Coord(jsonObject.getJSONObject("coord"))

    constructor(jsonString: String) : this(JSONObject(jsonString))
}