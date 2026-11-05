package fhnw.emoba.modules.module05.cities_solution.data

import android.content.Context
import org.json.JSONArray
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.util.Locale


object CityRepository {
    private val CH = Locale.Builder().setLanguage("de").setRegion("CH").build()
    
    lateinit var data: List<City>
    
    fun search(nameFilter: String, countryCodeFilter: String): List<City> {
        if(nameFilter.isBlank()){
            return emptyList()
        }
        else {
            return data.filter {
                it.name.lowercase(CH).contains(nameFilter.lowercase(CH)) &&
                        countryCodeFilter.contains(it.countryCode)
            }
        }
    }
    
    fun loadCities(context: Context) {
        val cities = mutableListOf<City>()
        
        val json = JSONArray(loadFromAsset(context, "citylist.json"))
        
        for (i in 0 until json.length()) {
            cities.add(City(json.getJSONObject(i)))
        }
        
        data = cities
    }
    
    private fun loadFromAsset(context: Context, fileName: String): String {
        val inputStream: InputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        
        return String(buffer, StandardCharsets.UTF_8)
    }
}