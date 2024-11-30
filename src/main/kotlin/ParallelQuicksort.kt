package ru.ntcrckr

import kotlinx.coroutines.*
import kotlin.time.measureTimedValue

suspend inline fun <R> measured(text: String, block: suspend () -> R): R {
    val (result, time) = measureTimedValue { block.invoke() }
    println("$text: $time")
    return result
}

suspend fun IntArray.parallelQuicksort(chunkSize: Int = 1000): IntArray {
    val sortedChunks = measured("parallel sort") { quicksortChunked(chunkSize) }
    return measured("merge") { merge(sortedChunks) }
}

private suspend fun IntArray.quicksortChunked(chunkSize: Int): List<IntArray> =
    coroutineScope {
        var start = 0
        val startSequence = generateSequence {
            start += chunkSize
            (start).takeIf { it < size }
        }
        startSequence
            .map {
                async<IntArray>(Dispatchers.Main) {
                    sliceArray(it..it + chunkSize - 1).quicksort()
//                    quicksort(it, it + chunkSize - 1).sliceArray(it..it + chunkSize - 1)
                }
            }
            .toList()
            .awaitAll<IntArray>()
    }

private suspend fun merge(sortedChunks: List<IntArray>): IntArray {
    val sorted = mutableListOf<Int>()
    val chunkIterators = sortedChunks.map { it.iterator() }.toMutableList()

    val nexts = chunkIterators.associate { it to it.nextInt() }.toMutableMap()
    measured("merge loop") {
        while (nexts.isNotEmpty()) {
            val currentMin = nexts.minBy { it.value }
            sorted.add(currentMin.value)
            if (currentMin.key.hasNext())
                nexts[currentMin.key] = currentMin.key.nextInt()
            else
                nexts.remove(currentMin.key)
        }
    }

    return sorted.toIntArray()
}