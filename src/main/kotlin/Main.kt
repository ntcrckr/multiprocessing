import CubicGraph.Node
import kotlinx.coroutines.*
import java.lang.System.currentTimeMillis

fun main() = runBlocking {
    val n = 500
    val repeatTimes = 5
    val startNode = Node(0, 0, 0)
    val cubicGraph = CubicGraph(n)

    val avgSeqTime = runMultipleTestsAndCheck(repeatTimes, cubicGraph) {
        bfsSeq(cubicGraph, startNode)
    }
    val avgParTime = runMultipleTestsAndCheck(repeatTimes, cubicGraph) {
        bfsPar(cubicGraph, startNode, numProcesses = 4)
    }

    println("Sequential BFS average time: %.2f seconds".format(avgSeqTime / 1000))
    println("Parallel BFS average time: %.2f seconds".format(avgParTime / 1000))
    println("Speedup: %.2fx".format(avgSeqTime / avgParTime))
}

private fun runMultipleTestsAndCheck(
    repeatTimes: Int,
    cubicGraph: CubicGraph,
    block: () -> IntArray,
): Double = (1..repeatTimes).map {
    val (result, time) = withTiming { block() }
    println(time / 1000f)
    checkResult(cubicGraph, result)
    time
}.average()

private fun <T> withTiming(block: () -> T): Pair<T, Long> {
    val startTime = currentTimeMillis()
    val result = block()
    val time = currentTimeMillis() - startTime
    return result to time
}

private fun checkResult(cubicGraph: CubicGraph, result: IntArray) {
    for (node in cubicGraph.nodes) {
        if (node.x + node.y + node.z != result[cubicGraph.getIndex(node)]) throw IllegalStateException()
    }
}