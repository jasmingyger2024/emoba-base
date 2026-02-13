package fhnw.emoba.modules.module06.weather_solution.data

import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


internal class CityTest{
    private lateinit var jsonString: String
    
    @Before
    fun setUp(){
        jsonString = """
            {"id": 7285348,
             "name": "Brugg",
             "state": "",
             "country": "CH",
             "coord": {
                 "lon": 8.21102,
                 "lat": 47.48629
                      }
             }
             """
    }
    
    @Test
    fun testConstructor(){
        //given
        val json = JSONObject(jsonString)
        
        //when
        val brugg = City(json)
        
        //then
        with(brugg) {
            assertEquals(7285348,  id)
            assertEquals("Brugg",  name)
            assertEquals("",       state)
            assertEquals("CH",     countryCode)
            assertEquals(8.21102,  coordinates.longitude, 0.01)
            assertEquals(47.48629, coordinates.latitude, 0.01)
        }
    }
    
    @Test
    fun testSecondaryConstructor(){
        //when
        val brugg = City(jsonString)
        
        //then
        with(brugg) {
            assertEquals("Brugg",  name)
            assertEquals("",       state)
            assertEquals("CH",     countryCode)
            assertEquals(8.21102,  coordinates.longitude, 0.01)
            assertEquals(47.48629, coordinates.latitude, 0.01)
        }
    }
    
    @Test
    fun testPrimaryConstructor(){
        //when
        val brugg = City(7285348, "Brugg", "", "CH", Coordinates(8.21102, 47.48629))
        
        //then
        with(brugg) {
            assertEquals(7285348,  id)
            assertEquals("Brugg",  name)
            assertEquals("",       state)
            assertEquals("CH",     countryCode)
            assertEquals(8.21102,  coordinates.longitude, 0.01)
            assertEquals(47.48629, coordinates.latitude, 0.01)
        }
    }
    
    @Test
    fun testAsJSON(){
        //given
        val brugg = City(jsonString)
        
        //when
        val json = brugg.asJson()
        
        val brugg2 = City(json)

        
        //then
        assertEquals(brugg, brugg2)
    }
}