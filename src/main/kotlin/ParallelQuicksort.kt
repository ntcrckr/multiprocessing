package ru.ntcrckr

import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
private val sortContext = newFixedThreadPoolContext(4, "sorting")

suspend fun IntArray.parallelQuicksort(
    left: Int = 0,
    right: Int = size,
) {
    if (right - left < 1000) {
        quicksort(left, right)
        return
    }

    val pivot = getPivot(left, right)
    sortContext {
        val leftJob = launch(sortContext) { parallelQuicksort(left, pivot) }
        val rightJob = launch(sortContext) { parallelQuicksort(pivot + 1, right) }
        leftJob.join()
        rightJob.join()
    }
}