package fhnw.emoba.modules.module06.weather_solution.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.ZoneOffset

class Forecast(val city: City, json: JSONObject) {
    val current = Current(json)
    val forcastDayArray = json.getJSONObject("forecast").getJSONArray("forecastday")
    val currentDay = forcastDayArray.getJSONObject(0)

    val nextHours = currentDay.getJSONArray("hour").map { Hourly(it) }

    constructor(city: City, jsonString: String) : this(city, JSONObject(jsonString))
}


abstract class Base(json: JSONObject) {

    val dateTime: LocalDateTime = if (json.has("time_epoch")) {
        LocalDateTime.ofEpochSecond(json.getLong("time_epoch"),0,ZoneOffset.ofTotalSeconds(0))
    } else if (json.has("last_updated_epoch")) {
        LocalDateTime.ofEpochSecond(json.getLong("last_updated_epoch"),0,ZoneOffset.ofTotalSeconds(0))
    } else {
        LocalDateTime.ofEpochSecond(0,0,ZoneOffset.ofTotalSeconds(0))
    }
    val temperature = json.getDouble("temp_c")
    val pressure = json.getDouble("pressure_mb")
    val humidity = json.getDouble("humidity")
    val dew_point = json.getDouble("dewpoint_c")
    val clouds = json.getDouble("cloud")
    val visibility = json.getDouble("vis_km")
    val windSpeed = json.getDouble("wind_kph")
    val windDeg = json.getDouble("wind_degree")
    val rain = if (json.has("rain") && json.getJSONObject("rain").has("1h")) {
        json.getJSONObject("rain").getDouble("1h")
    } else {
        0.0
    }
    val weatherDescription = json.getJSONObject("condition").getString("text")
    val weatherIcon: String = json.getJSONObject("condition").optString("icon", "")

    var weatherImage by mutableStateOf(DEFAULT_ICON.asImageBitmap())
}


class Current(json: JSONObject) : Base(json.getJSONObject("current")) {
    val forcastDayArray = json.getJSONObject("forecast").getJSONArray("forecastday")
    val currentDay = forcastDayArray.getJSONObject(0)
    val currentAstro = currentDay.getJSONObject("astro")
    val sunrise = currentAstro.getString("sunrise")
    val sunset = currentAstro.getString("sunset")
    val uvi = json.getJSONObject("current").getDouble("uv")
}

class Hourly(json: JSONObject) : Base(json) {
    val pop = json.getDouble("chance_of_rain")
}