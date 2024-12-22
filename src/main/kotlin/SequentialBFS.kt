import java.util.LinkedList
import java.util.Queue


// Sequential BFS
fun bfsSeq(graph: Map<Triple<Int, Int, Int>, List<Triple<Int, Int, Int>>>, start: Triple<Int, Int, Int>): Set<Triple<Int, Int, Int>> {
    val visited = HashSet<Triple<Int, Int, Int>>()
    val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
    queue.add(start)
    visited.add(start)

    while (queue.isNotEmpty()) {
        val node = queue.poll()
        for (neighbor in graph[node] ?: emptyList()) {
            if (neighbor !in visited) {
                visited.add(neighbor)
                queue.add(neighbor)
            }
        }
    }

    return visited
}