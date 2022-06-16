package utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class NodeTest {
    @Test()
    fun `test creation`() {

        val intArray = Array(3){ it }
        val intList = intArray.toList()
        val nodes1 = linkedNodesOf(*intArray)

        assertEquals(intList, nodes1.toList())
        assertEquals(intList, nodes1.mapValue { it })

        assertTrue(
            linkedNodesOf(1,2,4) == linkedNodesOf(1,2,4)
        )
        assertTrue(
            linkedNodesOf(1,2,4) == listOf(
                Node(1),Node(2),Node(4),
            ).apply { linked() }[0]
        )

        // test clone
        val nodes1Clone = nodes1.clone()
        assertFalse(nodes1 === nodes1Clone)
        var node1Pt : IntNode? = nodes1
        var node2Pt : IntNode? = nodes1Clone
        while (true) {
            assertTrue((node1Pt == null) == (node2Pt == null))
            assertFalse(node1Pt === node2Pt)
            assertTrue(node1Pt?.`val` === node2Pt?.`val`)
            node1Pt = node1Pt?.next
            node2Pt = node2Pt?.next
            if (node1Pt == null && node2Pt == null) break

        }
    }

}