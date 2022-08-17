@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.assertEqualTo
import utils.runTimedTests
import java.util.*


// 1584. Min Cost to Connect All Points
// https://leetcode.com/problems/min-cost-to-connect-all-points/
private interface Leetcode_1584 {
    fun minCostConnectPoints(points: Array<IntArray>): Int
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                arrayOf(intArrayOf(0, 0), intArrayOf(2, 2), intArrayOf(3, 10), intArrayOf(5, 2), intArrayOf(7, 0)) to 20,
                // [[3,12],[-2,5],[-4,1]]
                arrayOf(intArrayOf(3,12), intArrayOf(-2,5), intArrayOf(-4,1)) to 18,
                // [[2,-3],[-17,-8],[13,8],[-17,-15]]
                arrayOf(
                    intArrayOf(2,-3), intArrayOf(-17,-8), intArrayOf(13,8), intArrayOf(-17,-15),
                ) to 53,
            )
            listOf(
                // M1()::minCostConnectPoints,
                // M2()::minCostConnectPoints,
                S1()::minCostConnectPoints,
                S2()::minCostConnectPoints,
                S3()::minCostConnectPoints,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    // Wrong
    private class M1 : Leetcode_1584 {
        val map = HashMap<IntArray, HashMap<IntArray, Int>>()
        data class Dis(
            val p1: IntArray,
            val p2: IntArray,
            val dis: Int
        )
        override fun minCostConnectPoints(points: Array<IntArray>): Int {
            if (points.size <= 1) return 0
            if (points.size == 2) return dis(points[0], points[1])
            var cost = 0
            var costCount = 0

            val disQueue = PriorityQueue<Dis>(){ p1, p2 ->
                p1.dis - p2.dis
            }
            for (i in 0 until points.size - 1) {
                for (j in i+1 until points.size) {
                    val dis = dis(points[i], points[j])
                    disQueue.offer(Dis(points[i], points[j], dis))
                }
            }
            val set = HashSet<Pair<IntArray, IntArray>>()
            // val set = HashSet<IntArray>()

            while(set.size != points.size /* || costCount < points.size-1 */) {
                val dis = disQueue.poll()
                if (dis.p1 to dis.p2 !in set && dis.p2 to dis.p1 !in set) {
                    // if (dis.p1 == dis.p2) continue
                    set.add(dis.p1 to dis.p2)
                    // set.add(dis.p2)
                    println("[${dis.p1[0]},${dis.p1[1]}] [${dis.p2[0]},${dis.p2[1]}] : ${dis.dis}")
                    cost += dis.dis
                    costCount ++
                }
            }
            println("res cost:$cost")
            return cost

        }

        fun dis(p1: IntArray, p2: IntArray) : Int {
            if (map[p1] != null && map[p1]!![p2] != null) return map[p1]!![p2]!!
            if (map[p2] != null && map[p2]!![p1] != null) return map[p2]!![p1]!!
            val res = Math.abs(p1[0]-p2[0]) + Math.abs(p1[1]-p2[1])
            if (map[p1] == null) {
                map[p1] = HashMap()
            }
            map[p1]!!.put(p2, res)

            if (map[p2] == null) {
                map[p2] = HashMap()
            }
            map[p2]!!.put(p1, res)
            return res
        }
    }

    // Time Limit Exceeded
    private class M2 : Leetcode_1584 {
        data class Dist(
            val p1: IntArray,
            val p2: IntArray,
            val dist: Int
        )

        override fun minCostConnectPoints(points: Array<IntArray>): Int {
            val map = HashMap<IntArray, HashMap<IntArray, Int>>()

            for (i in 0 until points.size - 1) {
                val p1 = points[i]
                for (j in i + 1 until points.size) {
                    val p2 = points[j]
                    val dis = dis(p1, p2)
                    if (map[p1] == null) {
                        map[p1] = HashMap()
                    }
                    map[p1]!![p2] = dis

                    // anyway to avoid duplicates here?
                    if (map[p2] == null) {
                        map[p2] = HashMap()
                    }
                    map[p2]!![p1] = dis
                }
            }

            var cost = 0
            val set = HashSet<IntArray>()
            val heapQ = PriorityQueue<Dist>(){ a, b ->
                a.dist - b.dist
            }


            var p: IntArray = points[0]
            while(p !in set) {
                set.add(p)
                map[p]?.forEach{ p2, dist ->
                    heapQ.offer(Dist(p, p2, dist))
                }

                // map.forEach { p1, m2 ->
                //     m2.forEach{ p2, dist ->
                //         if (p1 == p || p2 == p) {
                //             heapQ.offer(Dist(p1, p2, dist))
                //         }
                //     }
                // }
                while(set.size != points.size){
                    val t = heapQ.poll()
                    val p1 = t.p1
                    val p2 = t.p2

                    // if (p2 in set) continue
                    if (p2 in set && p1 in set) {
                        println("skipped - p1:[${p1[0]},${p1[1]}] p2:[${p2[0]},${p2[1]}] dist:${t.dist}")
                        continue
                    }

                    cost += t.dist

                    if (p1 in set) p = p2 else p = p1
                    // p = p2
                    println("p1:[${p1[0]},${p1[1]}] p2:[${p2[0]},${p2[1]}] p:[${p[0]},${p[1]}] dist:${t.dist}")
                    break
                }
            }
            return cost
        }

        fun dis(p1: IntArray, p2: IntArray) : Int = Math.abs(p1[0]-p2[0]) + Math.abs(p1[1]-p2[1])
    }

    // Neetcode solution
    // Runtime: 4072 ms, faster than 5.66% of Kotlin online submissions for Min Cost to Connect All Points.
    // Memory Usage: 314.4 MB, less than 26.41% of Kotlin online submissions for Min Cost to Connect All Points.
    // https://leetcode.com/submissions/detail/774801321/
    private class S1 : Leetcode_1584{
        override fun minCostConnectPoints(points: Array<IntArray>): Int {
            val size = points.size
            val adj = HashMap<Int, ArrayList<Pair<Int, Int>>>()
            for (i in 0 until size) {
                val p1 = points[i]

                for (j in i + 1 until size) {
                    val p2 = points[j]
                    val dist = dis(p1, p2)
                    adj[i] = adj[i] ?: ArrayList()
                    adj[j] = adj[j] ?: ArrayList()
                    adj[i]!!.add(dist to j)
                    adj[j]!!.add(dist to i)
                }
            }

            // Prim's
            var res = 0
            val visit = HashSet<Int>()
            val minH = PriorityQueue<Pair<Int, Int>>{ a, b ->
                a.first - b.first
            }
            minH.offer(0 to 0)
            while (visit.size < size) {
                val (cost, i) = minH.poll()
                if (i in visit) continue
                res += cost
                visit.add(i)
                adj[i]?.forEach { (neiCost, nei) ->
                    if (nei !in visit) {
                        minH.offer(neiCost to nei)
                    }
                }
            }

            return res
        }

        fun dis(p1: IntArray, p2: IntArray) : Int = Math.abs(p1[0]-p2[0]) + Math.abs(p1[1]-p2[1])
    }

    private class S2 : Leetcode_1584 {
        override fun minCostConnectPoints(points: Array<IntArray>): Int {
            val mc = IntArray(points.size){Int.MAX_VALUE}
            val visited = BooleanArray(points.size){false}
            val pq = PriorityQueue<Pair<Int, Int>>{a,b -> a.second - b.second}

            mc[0] = 0
            pq.add(Pair(0, 0))

            while(!pq.isEmpty()){
                val node = pq.poll().first
                if(visited[node]) continue
                visited[node] = true

                for(i in 0..points.size-1){
                    if(i == node) continue

                    val weight = Math.abs(points[node][0] - points[i][0]) + Math.abs(points[node][1] - points[i][1])

                    if(!visited[i] && mc[i] > weight){
                        mc[i] = weight
                        pq.add(Pair(i, mc[i]))
                    }
                }
            }

            var total = 0
            mc.forEach{ total += it }
            return total
        }
    }

    // https://leetcode.cn/problems/min-cost-to-connect-all-points/solution/lian-jie-suo-you-dian-de-zui-xiao-fei-yo-kcx7/
    private class S3 : Leetcode_1584 {
        override fun minCostConnectPoints(points: Array<IntArray>): Int {
            val n = points.size
            val dsu = DisjointSetUnion(n)
            val edges: MutableList<Edge> = ArrayList()
            for (i in 0 until n) {
                for (j in i + 1 until n) {
                    edges.add(Edge(dist(points, i, j), i, j))
                }
            }
            edges.sortWith { a, b -> a.len - b.len }

            var ret = 0
            var num = 1
            for (edge in edges) {
                val len = edge.len
                val x = edge.x
                val y = edge.y
                if (dsu.unionSet(x, y)) {
                    ret += len
                    num++
                    if (num == n) {
                        break
                    }
                }
            }
            return ret
        }

        fun dist(points: Array<IntArray>, x: Int, y: Int): Int {
            return Math.abs(points[x][0] - points[y][0]) + Math.abs(points[x][1] - points[y][1])
        }
    }

    private class DisjointSetUnion(var n: Int) {
        var f: IntArray
        var rank: IntArray

        init {
            rank = IntArray(n)
            Arrays.fill(rank, 1)
            f = IntArray(n)
            for (i in 0 until n) {
                f[i] = i
            }
        }

        fun find(x: Int): Int {
            return if (f[x] == x) x else find(f[x]).also { f[x] = it }
        }

        fun unionSet(x: Int, y: Int): Boolean {
            var fx = find(x)
            var fy = find(y)
            if (fx == fy) {
                return false
            }
            if (rank[fx] < rank[fy]) {
                val temp = fx
                fx = fy
                fy = temp
            }
            rank[fx] += rank[fy]
            f[fy] = fx
            return true
        }
    }

    private class Edge(var len: Int, var x: Int, var y: Int)

}


val _20220815 = listOf<Testable>(
    Leetcode_1584,
)