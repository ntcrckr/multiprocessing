// Function to generate adjacency list for cubic graph with side n
fun generateCubicGraph(n: Int): Map<Triple<Int, Int, Int>, List<Triple<Int, Int, Int>>> {
    val adjacencyList = mutableMapOf<Triple<Int, Int, Int>, MutableList<Triple<Int, Int, Int>>>()
    val directions = listOf(
        Triple(-1, 0, 0), Triple(1, 0, 0),
        Triple(0, -1, 0), Triple(0, 1, 0),
        Triple(0, 0, -1), Triple(0, 0, 1)
    )

    for (x in 0 until n) {
        for (y in 0 until n) {
            for (z in 0 until n) {
                val neighbors = mutableListOf<Triple<Int, Int, Int>>()
                for ((dx, dy, dz) in directions) {
                    val nx = x + dx
                    val ny = y + dy
                    val nz = z + dz
                    if (nx in 0 until n && ny in 0 until n && nz in 0 until n) {
                        neighbors.add(Triple(nx, ny, nz))
                    }
                }
                adjacencyList[Triple(x, y, z)] = neighbors
            }
        }
    }

    return adjacencyList
}