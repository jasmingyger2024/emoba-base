package fhnw.emoba.modules.module05.cities.viewmodel

import androidx.lifecycle.ViewModel
import fhnw.emoba.modules.module05.cities.data.City
import fhnw.emoba.modules.module05.cities.data.CityRepository


class CitiesModel : ViewModel() {

    lateinit var cities: List<City>

    fun loadCities(){


    }

//    val filteredCities: List<City>
//        // something like this
//        by derivedStateOf(Cities.filter(City.name.contains("$search")))
//        get() = if (selectedTab == TabId.ALL) {
//            beers
//        } else {
//            beers.filter { it.beercolor == selectedTab.title }
//        }

}