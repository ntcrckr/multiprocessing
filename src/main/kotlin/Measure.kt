package ru.ntcrckr

import kotlin.time.Duration
import kotlin.time.measureTime

suspend inline fun measured(text: String, block: suspend () -> Unit): Duration {
    val time = measureTime { block.invoke() }
    println("$text: $time")
    return time
}