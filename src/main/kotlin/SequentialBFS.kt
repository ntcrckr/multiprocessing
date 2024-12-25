import CubicGraph.Node
import java.util.LinkedList
import java.util.Queue

fun bfsSeq(graph: CubicGraph, start: Node): IntArray {
    val queue: Queue<Node> = LinkedList()
    val result = IntArray(graph.nodes.size) { -1 }

    result[graph.getIndex(start)] += 1
    queue.add(start)

    while (queue.isNotEmpty()) {
        val node = queue.poll()

        for (neighbour in graph.getNeighbours(node)) {
            val neighbourIndex = graph.getIndex(neighbour)

            if (result[neighbourIndex] == -1) {
                val nodeIndex = graph.getIndex(node)
                result[neighbourIndex] = result[nodeIndex] + 1
                queue.add(neighbour)
            }
        }
    }

    return result
}