package utils

typealias IntNode = Node<Int>
typealias ListNode = IntNode

data class Node<T>(var `val`: T, var next: Node<T>? = null)


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

