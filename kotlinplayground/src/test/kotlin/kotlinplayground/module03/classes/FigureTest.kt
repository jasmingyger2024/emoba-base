package kotlinplayground.module03.classes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FigureTest {

    @Test
    fun testFigureConstructor () {
        var xPos = 5;
        var yPos = 10;

        val someFigure = Figure(xPos, yPos)

        assertEquals(xPos, someFigure.xPos)
        assertEquals(yPos, someFigure.yPos)
    }

    @Test
    fun testFigureArea () {
        val f = Figure()

        assertEquals(0.0, f.area())
    }

    @Test
    fun testCircleConstructor () {
        var radius = 10.0
        var x =  100
        var y = 100

        val someCircle = Circle(radius, x, y)

        assertEquals(radius, someCircle.radius)
        assertEquals(x, someCircle.xPos)
        assertEquals(y, someCircle.yPos)
    }

    @Test
    fun testCircleArea () {
        var radius = 10.0
        var x = 100
        var y = 100

        val c = Circle(radius, x, y)

        assertEquals(radius * radius * Math.PI, c.area())
    }
}

