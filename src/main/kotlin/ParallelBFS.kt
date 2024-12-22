import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

// Parallel BFS
suspend fun bfsPar(
    graph: Map<Triple<Int, Int, Int>, List<Triple<Int, Int, Int>>>,
    start: Triple<Int, Int, Int>,
    numProcesses: Int
): Set<Triple<Int, Int, Int>> {
    val visited = HashSet<Triple<Int, Int, Int>>()
    var level = setOf(start)
    visited.add(start)

    while (level.isNotEmpty()) {
        val chunks = level.chunked(level.size / numProcesses + 1)
        val deferred = chunks.map { chunk ->
            CoroutineScope(Dispatchers.Default).async {
                bfsWorker(graph, chunk.toSet(), visited)
            }
        }

        val newLevel = deferred.awaitAll().flatten().toSet()
        visited.addAll(newLevel)
        level = newLevel
    }

    return visited
}