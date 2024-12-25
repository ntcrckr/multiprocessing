import CubicGraph.Node
import java.util.concurrent.ForkJoinPool

fun bfsPar(
    graph: CubicGraph,
    start: Node,
    numProcesses: Int
): IntArray {
    val forkPool = ForkJoinPool(numProcesses)

    val result = IntArray(graph.nodes.size) { -1 }
    result[graph.getIndex(start)] += 1

    var front: MutableList<Node> = mutableListOf()
    front.add(start)

    while (front.isNotEmpty()) {
        val worker = bfsWorker(graph, result)
        val task = {
            front.parallelStream()
                .flatMap { worker(it) }
                .toList()
        }
        front = forkPool.submit(task).get()
    }

    return result
}