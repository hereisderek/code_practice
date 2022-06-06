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

    }
}