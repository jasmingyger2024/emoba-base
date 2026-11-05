package fhnw.emoba.modules.module05.cities_solution.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fhnw.emoba.modules.module05.cities_solution.data.City
import fhnw.emoba.modules.module05.cities_solution.data.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.ramani.compose.CameraPosition

class CitiesModel : ViewModel() {

    val title = "Städte dieser Welt"
    lateinit var repo: CityRepository
    var cityFilter by mutableStateOf("")
    var searchInCH by mutableStateOf(true)
    var searchInDE by mutableStateOf(false)
    private var isLoading by mutableStateOf(false)
    var currentCityList: List<City> by mutableStateOf(emptyList())
    var selectedCity: City? by mutableStateOf(null)
    val brugg = LatLng(latitude = 47.48149026165488, longitude = 8.211623564471637)

    var mapStyle: Style.Builder

    init {
        //Map Style (Tiles) from openfreemap.org
        val styleAttr: Style.Builder = Style.Builder().fromUri("https://tiles.openfreemap.org/styles/liberty")
        mapStyle = styleAttr
    }

    var mapCameraPosition by mutableStateOf(
        CameraPosition(
            target = brugg,
            zoom = 8.0,
            tilt = 0.0,
            bearing = 0.0,
        )
    )

    fun setCitiesRepository(repository: CityRepository){
        repo = repository
    }

    fun selectCity(city: City) {
        selectedCity = city
        val selectedLatLng = LatLng(latitude = city.coordinates.latitude, longitude = city.coordinates.longitude)
        mapCameraPosition = CameraPosition(
            target = selectedLatLng,
            zoom = 8.0,
            tilt = 0.0,
            bearing = 0.0
        )
    }


    fun launchSearch() {
        isLoading = true
        currentCityList = emptyList()
        // Service-Calls sollen asynchron erfolgen (Thema in der naechsten Woche)
        viewModelScope.launch(Dispatchers.IO) {
            var countryCodeFilter = ""
            if (searchInCH) {
                countryCodeFilter += "CH,"
            }
            if (searchInDE) {
                countryCodeFilter += "DE"
            }

            currentCityList = repo.search(cityFilter, countryCodeFilter)
            val city = currentCityList.firstOrNull()

            if (city != null) {
                selectCity(city)
            }
            isLoading = false
        }
    }
}