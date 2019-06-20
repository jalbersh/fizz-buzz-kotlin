package game

import org.junit.Test
import org.junit.Assert.assertEquals

class FizzBuzzJunitTest {

    @Test
    fun testNumber() {
        var fizzBuzz: FizzBuzzInterface = FizzBuzz()
        assertEquals(fizzBuzz.calculate(1),"1")
    }
    @Test
    fun testFizzNumber() {
        var fizzBuzz: FizzBuzzInterface = FizzBuzz()
        assertEquals(fizzBuzz.calculate(3),"Fizz")
    }
    @Test
    fun testBuzzNumber() {
        var fizzBuzz: FizzBuzzInterface = FizzBuzz()
        assertEquals(fizzBuzz.calculate(5),"Buzz")
    }
    @Test
    fun testFizzBuzzNumber() {
        var fizzBuzz: FizzBuzzInterface = FizzBuzz()
        assertEquals(fizzBuzz.calculate(15),"FizzBuzz")
    }
    @Test
    fun testAnotherNumber() {
        var fizzBuzz: FizzBuzzInterface = FizzBuzz()
        assertEquals(fizzBuzz.calculate(16),"16")
    }
}