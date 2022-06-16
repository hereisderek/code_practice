package utils

typealias IntNode = Node<Int>
typealias ListNode = IntNode

// @Suppress("NOTHING_TO_INLINE", "FunctionName")
// inline fun IntNode() = IntNode(-1, null)
//
// @Suppress("NOTHING_TO_INLINE", "FunctionName")
// inline fun ListNode(`val`: Int = -1, next: ListNode? = null) = ListNode(-1, null)

data class Node<T>(var `val`: T, var next: Node<T>? = null) : Cloneable {
    val hasNext: Boolean get() = next != null
    override fun toString(): String = "Node(${`val`},${hasNext})"


    /**
     * returns a string in the form of `[1,2,3,4,5]`
     * <bold>Be cause:</bold> if the node has circular reference this will lead to stackoverflow and eventually a crash
     */
    fun toUnsafeString(): String {
        return "[" + unsafeValue()
    }

    private fun unsafeValue() : String {
        return if (this.next == null) "$`val`]" else "$`val`,${this.next!!.unsafeValue()}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val o = (other as? Node<T>) ?: return false
        if (!Generic<Node<T>>().isInstance(other)) return false

        var p1 : Node<T>? = this
        var p2 : Node<T>? = o

        while (p1 != null || p2 !== p1) {
            if ((p1 == null) != (p2 == null)) return false
            if (p1?.`val` != p2?.`val`) return false
            p1 = p1?.next
            p2 = p2?.next
        }
        return true
    }

    /**
     * create a deep clone of the linked node
     * This might throw a stackoverflow exception if it contains circle
     */
    public override fun clone(): Node<T> {
        val head : Node<T> = Node(`val`)
        var p1 : Node<T>? = this.next
        var p2 : Node<T> = head
        while (p1 != null) {
            val node = Node(p1.`val`)
            p2.next = node
            p2 = node
            p1 = p1.next
        }
        return head
    }
}


/// creation
fun <T> linkedNodesOf(vararg nodes: T) : Node<T> {
    require(nodes.isNotEmpty())
    val list = nodes.map { Node(it) }
    list.linked()
    return list[0]
}

/**
 * create a cycled linked node
 * @param values: values for each nodes
 * @param pos: index of the node the tail node linked to
 * @return the head of the constructed linked nodes
 */
fun cycledNodesOf(values: IntArray, pos: Int) : ListNode {
    val dummy = ListNode(-1)
    var tailTo: ListNode? = null
    var head = dummy
    for (i in values.indices) {
        val node = ListNode(values[i])
        head.next = node
        head = node
        if (i == pos) {
            tailTo = node
        }
    }
    head.next = tailTo
    return dummy.next!!
}

/**
 *
 * intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
 * listA - The first linked list.
 * listB - The second linked list.
 * skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
 * skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
 * The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.
 *
 */

fun intersectedNodes(
    headA:ListNode, headB:ListNode, skipA: Int, skipB: Int, intersectVal: Int
) : Pair<ListNode, ListNode> {
    if (intersectVal == 0) return Pair(headA, headB)

    var hA: ListNode? = headA
    var hB: ListNode? = headB
    for (i in 0 until skipA - 1) {
        hA = hA?.next
    }

    for (i in 0 until skipB - 1) {
        hB = hB?.next
    }

    if (hA?.next?.`val` != null) check(hA?.next?.`val` == intersectVal)
    if (hB?.next?.`val` != null) check(hB?.next?.`val` == intersectVal)
    hB?.next = hA?.next

    return Pair(headA, headB)
}





// Not sure we'd want this
/*
fun <T> linkedNodesOf(vararg nodes: Node<T>) : Node<T> {
    require(nodes.isNotEmpty()) {
        "initializing nodes cannot be empty"
    }
    for (i in nodes.indices) {
        if (i == 0) continue
        nodes[i - 1].next = nodes[i]
    }
    return nodes[0]
}
*/

operator fun <T> Node<T>.get(index: Int) : Node<T>? {
    if (index < 0) return null
    if (index == 0) return this

    var pt : Node<T>? = this
    for (i in 0 until index) {
        pt = pt?.next
    }
    return pt
}

fun <T> List<Node<T>>.linked(): Unit {
    if (isEmpty()) return
    for (i in indices) {
        if (i == 0) continue
        get(i - 1).next = get(i)
    }
}



fun <T, R> Node<T>.map(transform: ((T)->R)) : Node<R> {
    var pointer = this

    val newHead = Node(transform.invoke(this.`val`))
    var previous: Node<R> = newHead

    while (pointer.next != null) {
        val newR = Node(transform.invoke(pointer.`val`))
        previous.next = newR
        previous = newR
        pointer = pointer.next!!
    }
    return newHead
}

fun <T, R> Node<T>.mapValue(transform: ((T)->R)) : List<R> {
    val list = mutableListOf<R>()
    var pointer = this
    list.add(transform.invoke(`val`))
    while (pointer.next != null) {
        val next = pointer.next!!
        list.add(transform.invoke(next.`val`))
        pointer = next
    }
    return list
}

fun <T> Node<T>.toList() : List<T> {
    val list = mutableListOf<T>()
    var pointer = this
    list.add(`val`)
    while (pointer.next != null) {
        val next = pointer.next
        list.add(next!!.`val`)
        pointer = next
    }
    return list
}

