package utils

interface UnionFindI {
    val count: Int
    fun union(p: Int, q: Int)
    fun connected(p: Int, q: Int) : Boolean
}



class UnionFind(count: Int) : UnionFindI {
    private val parent = IntArray(count) { it }

    override var count: Int = count; private set

    override fun union(p: Int, q: Int) {
        val rootP = find(p)
        val rootQ = find(q)
        if (rootP == rootQ) return

        parent[rootQ] = rootP
        count--
    }

    override fun connected(p: Int, q: Int): Boolean {
        return find(p) == find(q)
    }

    private fun find(x: Int): Int {
        if (parent[x] != x) {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }
}