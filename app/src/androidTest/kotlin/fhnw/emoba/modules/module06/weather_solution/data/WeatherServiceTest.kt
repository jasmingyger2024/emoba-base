package fhnw.emoba.modules.module06.weather_solution.data

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import fhnw.emoba.modules.module06.weather_solution.data.impl.RemoteWeatherService
import fhnw.emoba.modules.module06.weather_solution.viewmodel.WeatherModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherServiceTest {
    lateinit var appContext: Context
    lateinit var cityRepo: CityRepository

    @Before
    fun setup() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        cityRepo = CityRepository(appContext)
    }
    
    @Test
    fun testForecast() {
        //given
        val weatherService = RemoteWeatherService()
        val brugg = cityRepo.getCity(2661374)
        val freiburg = cityRepo.getCity(2925177)
        
        //when
        val weatherInBrugg    = weatherService.requestForecast(brugg)
        val weatherInFreiburg = weatherService.requestForecast(freiburg)
        
        //then
        assertEquals(brugg, weatherInBrugg.city)
        assertTrue(24 <= weatherInBrugg.nextHours.size)
        assertEquals(freiburg, weatherInFreiburg.city)
        assertTrue(24 <= weatherInFreiburg.nextHours.size)
    }

    @Test
    fun testForecastFavorites() {
        runTest {
            //given
            val weatherService = RemoteWeatherService()
            val weatherModel = WeatherModel()
            weatherModel.weatherService = weatherService
            weatherModel.citiesRepo = cityRepo
            //when
            val job = weatherModel.getForecastsOfFavoritesAsync()
            //then
            job.join()
            assertTrue(weatherModel.forecasts.isNotEmpty())
            assertFalse(weatherModel.isLoading)
        }
    }
}