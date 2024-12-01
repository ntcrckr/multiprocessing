package ru.ntcrckr

import kotlinx.coroutines.*

suspend fun IntArray.parallelQuicksort(
    left: Int = 0,
    right: Int = size,
) {
    if (right - left < 1000) {
        quicksort(left, right)
        return
    }

    val pivot = getPivot(left, right)
    Dispatchers.Main {
        val leftJob = launch(Dispatchers.Main) { parallelQuicksort(left, pivot) }
        val rightJob = launch(Dispatchers.Main) { parallelQuicksort(pivot + 1, right) }
        leftJob.join()
        rightJob.join()
    }
}