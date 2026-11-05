package fhnw.emoba.modules.module05.cities_solution.data

import org.json.JSONObject
import java.util.Locale

data class Coordinates(val longitude: Double, val latitude: Double){
    fun dms() : String{
        return format(latitude, longitude)
    }
    
    private fun format(latitude: Double, longitude: Double) : String{
        val latCompassDirection = if (latitude > 0.0)  "N" else "S"
        val lonCompassDirection = if (longitude > 0.0) "E" else "W"
        
        return "${getDMS(latitude)} $latCompassDirection, ${getDMS(longitude)} $lonCompassDirection"
    }
    
    private fun getDMS(value: Double): String {
        val absValue = Math.abs(value)
        val degree   = absValue.toInt()
        val minutes  = ((absValue - degree) * 60.0).toInt()
        val seconds  = (absValue - degree - minutes / 60.0) * 3600.0
        
        return "${degree}° ${minutes}′ ${String.format(Locale.ENGLISH, "%.4f", seconds)}″"
    }
}

class City(json: JSONObject){
    val id          = json.getInt("id")
    val name        = json.getString("name")
    val state       = json.getString("state")
    val countryCode = json.getString("country")
    val coordinates = Coordinates(json.getJSONObject("coord").getDouble("lon"),
                                  json.getJSONObject("coord").getDouble("lat"))
    
    constructor(jsonString: String) : this(JSONObject(jsonString))

    fun asJson(): String {
        return """
                {
                "id":      $id,
                "name":    "$name",
                "state":   "$state",
                "country": "$countryCode",
                "coord":   {
                            "lon": ${coordinates.longitude},
                            "lat": ${coordinates.latitude}
                            }
                }
        """.trimIndent()
        
    }
}