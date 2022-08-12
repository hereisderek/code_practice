package utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class UnionFindTest {
    @Test
    fun `test Union Find`() {
        UnionFind(10).apply {
            count.assertEqualTo(10)
            connected(0, 1).assertEqualTo(false)
            connected(2, 1).assertEqualTo(false)
            union(0, 1)
            connected(0, 1).assertEqualTo(true)
            connected(2, 1).assertEqualTo(false)
            count.assertEqualTo(9)
            union(0, 1)
            count.assertEqualTo(9)
            union(2, 1)
            count.assertEqualTo(8)
            connected(2, 1).assertEqualTo(true)
        }

    }
}

