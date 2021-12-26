package com.ruichaoqun.luckymusicv2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        runBlocking {
            flow {
                emit(1)
                delay(50)
                emit(2)
            }.collectLatest { value ->
                println("Collecting $value")
//                delay(100) // Emulate work
                println("$value collected")
            }
        }
    }


}