package kotlinplayground.module01

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


// ACHTUNG: im Kotlin-Playground arbeiten wir mit JUNIT 5
class SetupTest{

    @Test
    fun testJunitSetup(){
        //given
        val s1 = 1
        val s2 = 2

        //when
        val sum = s1 + s2

        //then
        assertEquals(3, sum)
    }
    
    @Test
    fun timeTest(){
        val dateTime = LocalDateTime.ofEpochSecond(1599332400, 0, ZoneOffset.ofTotalSeconds(7200))
    
        val date = dateTime.format(DateTimeFormatter.ofPattern("d.M.yyyy"))
        val time = dateTime.format(DateTimeFormatter.ofPattern("H:mm"))
        
        println(date)
        println(time)
        
    }

}