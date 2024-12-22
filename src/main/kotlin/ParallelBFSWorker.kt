// Parallel BFS worker function
suspend fun bfsWorker(
    graph: Map<Triple<Int, Int, Int>, List<Triple<Int, Int, Int>>>,
    level: Set<Triple<Int, Int, Int>>,
    visited: Set<Triple<Int, Int, Int>>
): Set<Triple<Int, Int, Int>> {
    val newLevel = HashSet<Triple<Int, Int, Int>>()
    for (node in level) {
        for (neighbor in graph[node] ?: emptyList()) {
            if (neighbor !in visited) {
                newLevel.add(neighbor)
            }
        }
    }
    return newLevel
}