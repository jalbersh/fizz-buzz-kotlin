package etc

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.*
import java.util.concurrent.*

fun main(args: Array<String>) {
    exampleWithContext()
}

suspend fun printlnDelayed(message: String) {
    delay(1000)
    println(message)
}

suspend fun calculateHardThings(startNum: Int): Int {
    delay(1000)
    return startNum*10
}

fun exampleBlocking() = runBlocking {
    println("one")
    printlnDelayed("two")
    println("three")
}

fun exampleBlockingDispatcher() {
    println("one - from thread ${Thread.currentThread().name}")
    runBlocking(Dispatchers.Default) {
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three = from thread ${Thread.currentThread().name}")
}

fun exampleLaunchGlobal() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")             // coroutine is light-weight thread, run in parallel, cheap
    GlobalScope.launch {                                                    // starts a coroutine
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three = from thread ${Thread.currentThread().name}")
    delay(3000)
}

fun exampleLaunchGlobalWaiting() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")
    val job = GlobalScope.launch {
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three = from thread ${Thread.currentThread().name}")
    job.join()
}

fun exampleLaunchCoroutineScope() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")
    val customDispatcher = Executors.newFixedThreadPool(2).asCoroutineDispatcher()
    launch(customDispatcher) {                  // since now using coroutine scope, the method waits for everything to finish
                                              // use Dispatchers.MAIN when on Android when manipulating UI, like textView.txt = "hey"
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three = from thread ${Thread.currentThread().name}")
    (customDispatcher.executor as ExecutorService).shutdown()
}

fun exampleAsyncAwait() = runBlocking {
    val startTime = System.currentTimeMillis()
    val deferred1 = async { calculateHardThings(10) }//.await()
    val deferred2 = async { calculateHardThings(20) }//.await() // with awaits here, takes over 3 seconds, in serial
    val deferred3 = async { calculateHardThings(30) }//.await()
    val sum = deferred1.await()  + deferred2.await()  + deferred3.await() // with awaits here, takes over 1 send, run concurrently
    val endTime = System.currentTimeMillis()
    println("async/await result = $sum in ${endTime-startTime} milliseconds")
}

fun exampleWithContext() = runBlocking {
    val startTime = System.currentTimeMillis()
    val result1 = withContext(Dispatchers.Default) { calculateHardThings(10) } // same as async-await
    val result2 = withContext(Dispatchers.Default) { calculateHardThings(20) } // takes over 3 seconds, in serial
    val result3 = withContext(Dispatchers.Default) { calculateHardThings(30) }
    val sum = result1  + result2  + result3
    val endTime = System.currentTimeMillis()
    println("async/await result = $sum in ${endTime-startTime} milliseconds")
}