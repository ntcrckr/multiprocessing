package ru.ntcrckr

fun IntArray.getPivot(left: Int, right: Int): Int {
    val pivotIdx = (left + right - 1) / 2
    swap(pivotIdx, left)
    val pivot = this[left]
    var start = left
    var end = right
    while (true) {
        do start++ while (start < right && this[start] < pivot)
        do end-- while (this[end] > pivot)
        if (start >= end) break
        swap(start, end)
    }
    swap(left, end)
    return end
}

private fun IntArray.swap(left: Int, end: Int) {
    this[left] = this[end].also { this[end] = this[left] }
}