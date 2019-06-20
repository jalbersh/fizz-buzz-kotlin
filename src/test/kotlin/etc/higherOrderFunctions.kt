package etc

import java.util.function.Consumer

fun main(args: Array<String>) {
    val program = Program()
    var result = 0

    program.sum(2,7)

    program.sum(2,7, object: MyInterface {
        override fun doit(sum: Int) {
            println("MyInterface.doit: $sum")
        }

    })

    var values = listOf<Int>(4,5,7,8,3,2,1,9)
    for (i in values) println("outer for loop: $i")
    values.forEach({println("inner forEach: $it")})

    var con: Consumer<Int> = object: Consumer<Int> {
        override fun accept(t: Int) {
            println("forEach consumer: $t")
        }
    }
    values.forEach(con) // long-hand way of showing consumer boilerplate code
    values.forEach({t -> println("short-hand lambda: $t")})
    values.forEach({println("even-shorter hand, if 1-arg: $it")})
//
//             Class      Behavioral
//              Name      Method
//                 \     /
    values.forEach(::println) //

//                         Signature
//                                Return         Method Closure changes outside variable
//        variable    parameters   Type   Params  Body  result (unlike Java)
//           |          |   |       |       |      |     |
    val mySumLambda: (Int, Int) -> Int = { a,b -> result = a+b; result }

    val myPrintLambda: (Int) -> Unit = { s: Int -> println("MyPrintLambda body: $s")}

    program.sum(3,4, myPrintLambda) // Passes Lambda variable as Parameter

    program.sum(1,2, mySumLambda) // Passes Lambda variable as Parameter
    println("outside variable changed in Lambda: $result")

    program.sum(5,6, { a,b -> a+b }) // Passes Lambda expression as Parameter

    program.sum(1,5) { a,b -> a+b } // Uses Lambda expression as Parameter, outside of method call

    program.sum(7,8, mySumLambda, myPrintLambda) // Passes 2 Lambda variables as Parameters

    program.sum(7,10, {s: Int -> println("Lambda expression: $s")}) // Passes lambda expression (function) as parameter instead of Lambda variable

    program.reverseAnDisplay("Hello", { s -> s.reversed()}) // lambda expression passed as parameter

    program.reverseAnDisplay("Hello using it", { it.reversed()}) // in case of 1-arg in lambda, can use it and remove ->

    val firstPerson = Person("John", 34, "Programmer")
    val secondPerson = Person("Betty", 35, "Secretary")
    var olderPerson = if (firstPerson.age >= secondPerson.age) firstPerson else secondPerson
    olderPerson.printPerson()

    run { // runs given body, returns return value from body; modifies outer val
        firstPerson.age += 1
        if (firstPerson.age >= secondPerson.age) firstPerson else secondPerson
    }.printPerson()

    with(firstPerson) {// with is rarely used, used run on object instead, as below; modifies outer val
        age += 1 // modifies outer val
        "age for $name is now $age"
    }.println()

    olderPerson = if (firstPerson.age >= secondPerson.age) firstPerson else secondPerson
    olderPerson.printPerson()

    firstPerson.run {// run modifies outer val
        age += 2
        "Age for $name is now $age"
    }.println()

    olderPerson = if (firstPerson.age >= secondPerson.age) firstPerson else secondPerson
    olderPerson.printPerson()

    firstPerson.let{ modifiedPerson -> // let modifies outer val
        modifiedPerson.age += 1
        "Age for ${modifiedPerson.name} is now ${modifiedPerson.age}"
    }.println()

    secondPerson.apply {// apply modifies outer val
        age += 1
        job = "Executive Secretary"
    }.printPerson()

    secondPerson.also {// also modifes val
        it.age += 1
        it.job = "YouTuber"
    }.printPerson()
}

data class Person(var name: String,
                  var age: Int,
                  var job: String) {
    fun printPerson() = println(this.toString())
}

fun String.println() = println(this)

class Program {
    // Hign level function either takes lambda function as paremter
    // or returns the lambda function as return value
    fun sum( a: Int, b: Int, action: (Int, Int) -> (Int)) { // High Level Funcion with Lambda as parameter to a higher order function
        val sum = action(a,b) // result = x+y = a+b
        println("3-arg sum: $sum")
    }
    fun sum(a: Int, b: Int, action: (Int) -> Unit): Unit { // High Level Funcion with Lambda as parameter
        val sum = a+b
        action(sum) // is replaced by body of Lambda or println(s)
    }
    fun sum(a: Int, b: Int, action1: (Int, Int) -> (Int), action2: (Int) -> Unit) {
        val sum = action1(a,b) // result = x+y = a+b
        action2(sum)
    }
    fun sum(a: Int, b: Int, action: MyInterface): Unit { // Uses Interface in the OO way
        val sum = a+b
        action.doit(sum)
    }
    fun sum(a: Int, b: Int): Unit {
        val sum = a+b
        println("2-arg sum: $sum")
    }
    fun reverseAnDisplay(str: String, myFunc: (String) -> String) {
        var result = myFunc(str) // it.reversed() ===> str.reversed() ===> "Hello".reversed() = "olleH"
        println("result of lambda function: $result")
    }
}

interface MyInterface {
    fun doit(sum: Int)
}

