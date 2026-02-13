package fhnw.emoba.modules.module05.coordinates.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.Locale
import kotlin.math.abs

//Todo: Code analysieren und verstehen. Es sind keine Aenderungen notwendig
class CoordinatesModel : ViewModel() {
    val title = "Lat/Lon Formatter"
    
    var latitude  by mutableDoubleStateOf(47.480995)
    var longitude by mutableDoubleStateOf( 8.211862)
    
    var coordinates by mutableStateOf("")
    
    init {
        convert()
    }
    
    fun convert() {
        coordinates = format(latitude, longitude)
    }
    
    private fun format(latitude: Double, longitude: Double) : String{
        val latCompassDirection = if (latitude > 0.0) "N" else "S"
        val lonCompassDirection = if (longitude > 0.0) "E" else "W"
        
        return "${getDMS(latitude)} $latCompassDirection, ${getDMS(longitude)} $lonCompassDirection"
    }
    
    private fun getDMS(value: Double): String {
        val absValue = abs(value)
        val degree   = absValue.toInt()
        val minutes  = ((absValue - degree) * 60.0).toInt()
        val seconds  = (absValue - degree - minutes / 60.0) * 3600.0
        
        return "${degree}° ${minutes}′ ${String.format(Locale.ENGLISH, "%.4f", seconds)}″"
    }
    
}