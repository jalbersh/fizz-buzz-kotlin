package etc

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.hamcrest.CoreMatchers.`is` as coreMatchersIs

class PrimeFactorsTest {
    @Test
    fun testPrimeFactorsOf1() {
        assertEquals(factorsOf(1), emptyList<Int>())
    }
    @Test
    fun testPrimeFactorsOf2() {
        assertEquals(factorsOf(2), listOf(2))
    }
    @Test
    fun testPrimeFactorsOf3() {
        assertEquals(factorsOf(3), listOf(3))
    }
    @Test
    fun testPrimeFactorsOf4() {
        assertEquals(factorsOf(4), listOf(2, 2))
    }
    @Test
    fun testPrimeFactorsOf5() {
        assertEquals(factorsOf(5), listOf(5))
    }
    @Test
    fun testPrimeFactorsOf6() {
        assertEquals(factorsOf(6), listOf(2, 3))
    }
    @Test
    fun testPrimeFactorsOf7() {
        assertEquals(factorsOf(7), listOf(7))
    }
    @Test
    fun testPrimeFactorsOf8() {
        assertEquals(factorsOf(8), listOf(2, 2, 2))
    }
    @Test
    fun testPrimeFactorsOf9() {
        assertEquals(factorsOf(9), listOf(3, 3))
    }
    @Test
    fun testPrimeFactorsOfLargeNumber() {
        assertEquals(factorsOf(2*2*3*3*5*7*11*11*13),
                        listOf(2,2,3,3,5,7,11,11,13))
    }

    //    public fun factorsOf(n: Int): List<Int> {  // regular fun
//    val factorsOf = { n: Int ->                // lambda fun
//    val factorsOf: (Int) -> List<Int> = { n -> // lambda fun
    var factorsOf = fun(n: Int): List<Int> {     // Anonymous fun
        var remainder:Int = n
        val factors = mutableListOf<Int>()
        var divisor = 2
        while (remainder>1) {
            while (remainder % divisor == 0) {
                factors.add(divisor)
                remainder /= divisor
            }
            divisor++
        }
        factors.forEach{it -> println(it)} // lambda in loop
        return factors
//        factors
    }
}