class CubicGraph(
    private val n: Int,
) {
    val nodes = mutableListOf<Node>()

    init {
        for (i in 0 until n)
            for (j in 0 until n)
                for (k in 0 until n)
                    nodes.add(Node(i, j, k))
    }

    fun getNeighbours(node: Node): List<Node> = buildList {
        if (node.x > 0)
            add(Node(node.x - 1, node.y, node.z))
        if (node.x < n - 1)
            add(Node(node.x + 1, node.y, node.z))
        if (node.y > 0)
            add(Node(node.x, node.y - 1, node.z))
        if (node.y < n - 1)
            add(Node(node.x, node.y + 1, node.z))
        if (node.z > 0)
            add(Node(node.x, node.y, node.z - 1))
        if (node.z < n - 1)
            add(Node(node.x, node.y, node.z + 1))
    }

    fun getIndex(node: Node): Int =
        node.x * n * n + node.y * n + node.z

    class Node(
        val x: Int,
        val y: Int,
        val z: Int,
    )
}