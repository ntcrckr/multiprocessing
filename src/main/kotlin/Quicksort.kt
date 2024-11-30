package ru.ntcrckr

fun IntArray.quicksort(left: Int = 0, right: Int = size - 1): IntArray {
    var start = left
    var end = right
    val pivot = this[(left + right) / 2]

    while (start <= end) {
        while (this[start] < pivot) {
            start++
        }
        while (this[end] > pivot) {
            end--
        }
        if (start <= end) {
            val temp = this[start]
            this[start] = this[end]
            this[end] = temp
            start++
            end--
        }
    }

    if (left < end) {
        quicksort(left, end)
    }
    if (start < right) {
        quicksort(start, right)
    }
    return this
}