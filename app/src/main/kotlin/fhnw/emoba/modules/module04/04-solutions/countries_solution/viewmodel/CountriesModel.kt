package fhnw.emoba.modules.module04.countries_solution.viewmodel

import androidx.lifecycle.ViewModel
import fhnw.emoba.modules.module04.countries_solution.data.Country
import fhnw.emoba.modules.module04.countries_solution.data.Repository

class CountriesModel : ViewModel() {
    val title = "Länder in Europa"
    
    lateinit var countries: List<Country>

    init {
        loadCountries()
    }
    
    private fun loadCountries(){
        countries = Repository.countries
    }
}