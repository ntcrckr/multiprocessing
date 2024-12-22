import kotlinx.coroutines.*

// Test the implementations
fun main() = runBlocking {
    val n = 500
    val startNode = Triple(0, 0, 0)
    val cubicGraph = generateCubicGraph(n)

    // Sequential BFS timing
    val seqTimes = mutableListOf<Long>()
    repeat(5) {
        val startTime = System.currentTimeMillis()
        bfsSeq(cubicGraph, startNode)
        seqTimes.add(System.currentTimeMillis() - startTime)
    }
    val avgSeqTime = seqTimes.average()

    // Parallel BFS timing
    val parTimes = mutableListOf<Long>()
    repeat(5) {
        val startTime = System.currentTimeMillis()
        bfsPar(cubicGraph, startNode, numProcesses = 4)
        parTimes.add(System.currentTimeMillis() - startTime)
    }
    val avgParTime = parTimes.average()

    println("Sequential BFS average time: %.2f seconds".format(avgSeqTime / 1000))
    println("Parallel BFS average time: %.2f seconds".format(avgParTime / 1000))
    println("Speedup: %.2fx".format(avgSeqTime / avgParTime))
}
