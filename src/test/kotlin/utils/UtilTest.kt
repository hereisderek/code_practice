package utils

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class UtilTest {

    @Test
    fun `test List contentEquals with ignore order and not`() {

        listOf("a", "b", "c").apply {
            val list_2 = listOf("a", "b", "c")
            val list_3 = listOf("a", "c", "b")
            val list_4 = listOf("a", "b", "c", "d")
            assertTrue(contentEquals(list_2, true))
            assertTrue(contentEquals(list_2, false))
            assertTrue(contentEquals(list_3, true))
            assertFalse(contentEquals(list_3, false))
            assertFalse(contentEquals(list_4, true))
        }

        listOf("a", "c", "c").apply {
            val test1 = listOf("a", "c")
            val test2 = listOf("a", "c", "a")
            assertFalse(contentEquals(test1, false))
            assertFalse(contentEquals(test1, false))
            assertFalse(contentEquals(test2, true))
            assertFalse(contentEquals(test2, false))
        }

    }
}