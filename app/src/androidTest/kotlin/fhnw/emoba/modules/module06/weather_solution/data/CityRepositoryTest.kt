package fhnw.emoba.modules.module06.weather_solution.data

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Locale

@RunWith(AndroidJUnit4::class)
class CityRepositoryTest {
    lateinit var appContext: Context
    
    @Before
    fun setup() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }
    
    
    @Test
    fun testCityRepository() {
        //when
        val repo = CityRepository(appContext)
        
        //then
        assertTrue(repo.data.isNotEmpty())
        assertEquals(2147714, repo.data.first().id)
        assertEquals(8051234, repo.data.last().id)
    }
    
    @Test
    fun testSearch() {
        //given
        val repo = CityRepository(appContext)
        
        //when
        var cities = repo.search("Brugg", "CH")
        
        //then
        assertTrue(cities.isNotEmpty())
        assertTrue(cities.stream().allMatch {
            it.name.lowercase(Locale.getDefault()).contains("brugg") &&
                    it.countryCode == "CH"
        })
        
        //when
        cities = repo.search("Brugg", "DE")
        
        //then
        assertEquals(1, cities.size)
        assertTrue(
            cities.first().name.lowercase(Locale.getDefault()).contains("brugg") &&
                           cities.first().countryCode == "DE")
    }
    
    @Test
    fun testGetCity(){
        //given
        val repo = CityRepository(appContext)
        val id = 2661374
    
        //when
        val brugg = repo.getCity(id)
        
        //then
        assertNotNull(brugg)
        assertEquals(id, brugg.id)
        assertEquals("Brugg", brugg.name)
        assertEquals("CH", brugg.countryCode)
    }
    
}