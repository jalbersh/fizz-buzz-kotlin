package game

public class FizzBuzz : FizzBuzzInterface {
    override fun calculate(number: Int): String {
        if (number.rem(3) == 0 && number % 5 == 0) return "FizzBuzz"
        if (number % 3 == 0) return "Fizz"
        if (number % 5 == 0) return "Buzz"
        return number.toString()
    }
}

public interface FizzBuzzInterface {
    fun calculate(number: Int): String
}