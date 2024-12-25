import CubicGraph.Node
import java.util.stream.Stream

fun bfsWorker(
    graph: CubicGraph,
    result: IntArray,
): (node: Node) -> Stream<Node> = { node ->
    val visited = ArrayList<Node>(6)

    for (neighbour in graph.getNeighbours(node)) {
        val neighbourIndex = graph.getIndex(neighbour)
        val nodeIndex = graph.getIndex(node)

        if (result[neighbourIndex] == -1 && result.cas(neighbourIndex, result[nodeIndex] + 1))
            visited.add(neighbour)
    }

    visited.stream()
}