package makarenko.interview.namesemails

class DisjointSet {
    private val forest: MutableMap<String, Node> = mutableMapOf()

    internal inner class Node {
        lateinit var email: String
        var parent: Node? = null
        var rank = 0
    }

    fun makeSet(email: String) {
        if (forest[email] != null)
            return
        val node = Node()
        node.email = email
        node.parent = node
        node.rank = 0
        forest[email] = node
    }

    fun findSet(email: String): String {
        return findSet(forest[email])!!.email
    }

    private fun findSet(node: Node?): Node? {
        val parent = node!!.parent
        if (parent === node) {
            return parent
        }
        node.parent = findSet(node.parent)
        return node.parent
    }

    // With union by rank optimization
    fun union(first: String, second: String): Boolean {
        val node1 = forest[first]
        val node2 = forest[second]
        val parent1 = findSet(node1)!!
        val parent2 = findSet(node2)!!

        if (parent1.email == parent2.email) {
            return false
        }

        if (parent1.rank >= parent2.rank) {
            parent1.rank = if (parent1.rank == parent2.rank) parent1.rank + 1 else parent1.rank
            parent2.parent = parent1
        } else {
            parent1.parent = parent2
        }
        return true
    }
}