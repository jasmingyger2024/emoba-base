package kotlinplayground.module01

fun main(){
    var y: Int
    y = 42
    println(42)

    var x = "Die Antwort ist $y"
    println(x)
    println(x.length)  // x ist sicher != null


    var nullableX: String? = null
    println(nullableX?.length)  // sog. Elvis-Operator '?'

    val z = "emoba"  // Konstante, der Wert kann nicht mehr veraendert werden
}