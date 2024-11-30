package ru.ntcrckr

import kotlin.random.Random
import kotlin.time.measureTime

suspend fun main() {
    val array = IntArray(1_000_000) { Random.nextInt() }
    println(measureTime { array.parallelQuicksort() })
}