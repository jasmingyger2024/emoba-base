package kotlinplayground.module04.canton

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CantonTest {

    @Test
    fun testEquals(){
        //given
        val someCanton = Canton(1)
        someCanton.name = "Zürich"
        val otherCanton = Canton(1)
        otherCanton.name = "Aargau"
        //then
        assertTrue(someCanton.equals(otherCanton))
    }
}