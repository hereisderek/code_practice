package utils

typealias IntNode = Node<Int>

data class Node<T>(var `val`: T, var next: Node<T>? = null)


fun <T> linkedNodesOf(vararg nodes: T) : Node<T> {
    require(nodes.isNotEmpty())
    val list = nodes.map { Node(it) }
    list.linked()
    return list[0]
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

