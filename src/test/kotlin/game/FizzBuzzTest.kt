package game

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.api.dsl.xon

class FizzBuzzTest : Spek ({

    var fizzBuzz: FizzBuzzInterface? = null//FizzBuzz()

    beforeEachTest {
        fizzBuzz = FizzBuzz()
    }

    // or given(), on(), it()
    describe(" Calculate Fizz Buzz logic") {
        on("do these") {
            it("check number") {
                assertEquals(fizzBuzz?.calculate(1), "1")
            }
            it("check another number") {
                assertEquals(fizzBuzz?.calculate(23), "23")
            }
            it("check fizz") {
                assertEquals(fizzBuzz?.calculate(3), "Fizz")
            }
            it("check another fizz") {
                assertEquals(fizzBuzz?.calculate(9), "Fizz")
            }
            it("check buzz") {
                assertEquals(fizzBuzz?.calculate(5), "Buzz")
            }
            it("check another buzz") {
                assertEquals(fizzBuzz?.calculate(25), "Buzz")
            }
            it("check FizzBuzz") {
                assertEquals(fizzBuzz?.calculate(15), "FizzBuzz")
            }
            it("check another FizzBuzz ") {
                assertEquals(fizzBuzz?.calculate(30), "FizzBuzz")
            }
        }
//        xon("ignore") {
//            it("returns the number if not 3 or 5") {
//                assertEquals(fizzBuzz?.calculate(1), "1")
//            }
//        }
    }
})
