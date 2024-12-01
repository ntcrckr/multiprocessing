package ru.ntcrckr

fun IntArray.quicksort(left: Int = 0, right: Int = size) {
    if (right - left < 2) return
    val pivot = getPivot(left, right)
    quicksort(left, pivot)
    quicksort(pivot + 1, right)
}