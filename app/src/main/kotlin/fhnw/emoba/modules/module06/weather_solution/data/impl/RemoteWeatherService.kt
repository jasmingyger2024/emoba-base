package fhnw.emoba.modules.module06.weather_solution.data.impl

import android.graphics.Bitmap
import fhnw.emoba.modules.module06.weather_solution.data.City
import fhnw.emoba.modules.module06.weather_solution.data.DEFAULT_ICON
import fhnw.emoba.modules.module06.weather_solution.data.Forecast
import fhnw.emoba.modules.module06.weather_solution.data.WeatherService
import fhnw.emoba.modules.module06.weather_solution.data.bitmap
import fhnw.emoba.modules.module06.weather_solution.data.content
import org.json.JSONObject

/**
 * https://www.weatherapi.com/docs/
 */
class RemoteWeatherService : WeatherService {

    override fun requestForecast(city: City): Forecast {
        val baseURL = "https://api.weatherapi.com/v1"
        val apiMethod = "/forecast.json"
        val apiKey =
            "5a74a212676048a498944657241109" // get your own https://www.weatherapi.com
        val lang = "de"
        val longitude = city.coordinates.longitude
        val latitude = city.coordinates.latitude

        try {
            val url = "$baseURL$apiMethod?q=$latitude,$longitude&lang=$lang&key=$apiKey"
            val result = content(url)
            Thread.sleep(333L) //to show spinner demo purpose
            return Forecast(city, result)
        } catch (e: Exception) {
            println(e.message)
            //Todo: better exception handling
            return Forecast(city, JSONObject("""{
                "location": {
                "name": "Error",
                "region": "",
                "country": "Error",
                "lat": 47.48,
                "lon": 8.21,
                "tz_id": "Europe/Zurich",
                "localtime_epoch": 1726038569,
                "localtime": "2024-09-11 09:09"
            },
                "current": {
                "last_updated_epoch": 1726038000,
                "last_updated": "2024-09-11 09:00",
                "temp_c": 99.3,
                "temp_f": 55.9,
                "is_day": 1,
                "condition": {
                "text": "Error",
                "icon": "//cdn.weatherapi.com/weather/64x64/day/116.png",
                "code": 1003
            },
                "wind_mph": 2.2,
                "wind_kph": 3.6,
                "wind_degree": 236,
                "wind_dir": "WSW",
                "pressure_mb": 1013.0,
                "pressure_in": 29.91,
                "precip_mm": 0.0,
                "precip_in": 0.0,
                "humidity": 77,
                "cloud": 75,
                "feelslike_c": 12.1,
                "feelslike_f": 53.8,
                "windchill_c": 12.6,
                "windchill_f": 54.6,
                "heatindex_c": 13.7,
                "heatindex_f": 56.6,
                "dewpoint_c": 8.9,
                "dewpoint_f": 48.0,
                "vis_km": 10.0,
                "vis_miles": 6.0,
                "uv": 4.0,
                "gust_mph": 9.8,
                "gust_kph": 15.7
            },
                "forecast": {
                "forecastday": [
                {
                    "date": "2024-09-11",
                    "date_epoch": 1726012800,
                    "day": {
                    "maxtemp_c": 14.5,
                    "maxtemp_f": 58.2,
                    "mintemp_c": 6.2,
                    "mintemp_f": 43.1,
                    "avgtemp_c": 10.2,
                    "avgtemp_f": 50.4,
                    "maxwind_mph": 8.9,
                    "maxwind_kph": 14.4,
                    "totalprecip_mm": 19.45,
                    "totalprecip_in": 0.77,
                    "totalsnow_cm": 0.0,
                    "avgvis_km": 8.3,
                    "avgvis_miles": 5.0,
                    "avghumidity": 91,
                    "daily_will_it_rain": 1,
                    "daily_chance_of_rain": 95,
                    "daily_will_it_snow": 0,
                    "daily_chance_of_snow": 0,
                    "condition": {
                    "text": "Error",
                    "icon": "//cdn.weatherapi.com/weather/64x64/day/302.png",
                    "code": 1189
                },
                    "uv": 2.0
                },
                    "astro": {
                    "sunrise": "07:00 AM",
                    "sunset": "07:47 PM",
                    "moonrise": "03:58 PM",
                    "moonset": "11:21 PM",
                    "moon_phase": "First Quarter",
                    "moon_illumination": 47,
                    "is_moon_up": 0,
                    "is_sun_up": 0
                },
                    "hour": [
                    {
                        "time_epoch": 1726005600,
                        "time": "2024-09-11 00:00",
                        "temp_c": 7.3,
                        "temp_f": 45.1,
                        "is_day": 0,
                        "condition": {
                        "text": "leicht bewölkt",
                        "icon": "//cdn.weatherapi.com/weather/64x64/night/116.png",
                        "code": 1003
                    },
                        "wind_mph": 5.1,
                        "wind_kph": 8.3,
                        "wind_degree": 241,
                        "wind_dir": "WSW",
                        "pressure_mb": 1015.0,
                        "pressure_in": 29.98,
                        "precip_mm": 0.0,
                        "precip_in": 0.0,
                        "snow_cm": 0.0,
                        "humidity": 89,
                        "cloud": 44,
                        "feelslike_c": 5.7,
                        "feelslike_f": 42.3,
                        "windchill_c": 5.7,
                        "windchill_f": 42.3,
                        "heatindex_c": 7.3,
                        "heatindex_f": 45.1,
                        "dewpoint_c": 5.6,
                        "dewpoint_f": 42.0,
                        "will_it_rain": 0,
                        "chance_of_rain": 0,
                        "will_it_snow": 0,
                        "chance_of_snow": 0,
                        "vis_km": 10.0,
                        "vis_miles": 6.0,
                        "gust_mph": 10.8,
                        "gust_kph": 17.4,
                        "uv": 0
                    },
                    {
                        "time_epoch": 1726009200,
                        "time": "2024-09-11 01:00",
                        "temp_c": 7.1,
                        "temp_f": 44.7,
                        "is_day": 0,
                        "condition": {
                        "text": "Klar",
                        "icon": "//cdn.weatherapi.com/weather/64x64/night/113.png",
                        "code": 1000
                    },
                        "wind_mph": 4.7,
                        "wind_kph": 7.6,
                        "wind_degree": 230,
                        "wind_dir": "SW",
                        "pressure_mb": 1015.0,
                        "pressure_in": 29.96,
                        "precip_mm": 0.0,
                        "precip_in": 0.0,
                        "snow_cm": 0.0,
                        "humidity": 89,
                        "cloud": 14,
                        "feelslike_c": 5.7,
                        "feelslike_f": 42.2,
                        "windchill_c": 5.7,
                        "windchill_f": 42.2,
                        "heatindex_c": 7.1,
                        "heatindex_f": 44.7,
                        "dewpoint_c": 5.4,
                        "dewpoint_f": 41.7,
                        "will_it_rain": 0,
                        "chance_of_rain": 0,
                        "will_it_snow": 0,
                        "chance_of_snow": 0,
                        "vis_km": 10.0,
                        "vis_miles": 6.0,
                        "gust_mph": 9.9,
                        "gust_kph": 15.9,
                        "uv": 0
                    },
                    {
                        "time_epoch": 1726012800,
                        "time": "2024-09-11 02:00",
                        "temp_c": 7.5,
                        "temp_f": 45.5,
                        "is_day": 0,
                        "condition": {
                        "text": "Klar",
                        "icon": "//cdn.weatherapi.com/weather/64x64/night/113.png",
                        "code": 1000
                    },
                        "wind_mph": 5.1,
                        "wind_kph": 8.3,
                        "wind_degree": 229,
                        "wind_dir": "SW",
                        "pressure_mb": 1014.0,
                        "pressure_in": 29.95,
                        "precip_mm": 0.0,
                        "precip_in": 0.0,
                        "snow_cm": 0.0,
                        "humidity": 89,
                        "cloud": 9,
                        "feelslike_c": 6.0,
                        "feelslike_f": 42.8,
                        "windchill_c": 6.0,
                        "windchill_f": 42.8,
                        "heatindex_c": 7.5,
                        "heatindex_f": 45.5,
                        "dewpoint_c": 5.8,
                        "dewpoint_f": 42.4,
                        "will_it_rain": 0,
                        "chance_of_rain": 0,
                        "will_it_snow": 0,
                        "chance_of_snow": 0,
                        "vis_km": 10.0,
                        "vis_miles": 6.0,
                        "gust_mph": 10.8,
                        "gust_kph": 17.4,
                        "uv": 0
                    }
                    ]
                }
                ]
            }
            }"""
                )
            )
        }
    }

    override fun requestWeatherIcon(icon: String): Bitmap {
        return try {
            bitmap("https:$icon")
        } catch (e: Exception) {
            DEFAULT_ICON
        }
    }
}