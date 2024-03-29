package utils

interface Tuple<A, B, C> {
    val first: A
    val second: B
    val third: C
    fun toStr() = "Tuple(${toStr(first)}, ${toStr(second)}, ${toStr(third)})"
}


class ImmutableTuple<A, B, C>(
    override val first: A,
    override val second: B,
    override val third: C,
) : Tuple<A, B, C> {
    override fun toString(): String {
        return super.toStr()
    }
}

class MutableTuple<A, B, C>(
    override var first: A,
    override var second: B,
    override var third: C,
) : Tuple<A, B, C> {
    override fun toString(): String {
        return super.toStr()
    }
}


fun <A, B, C> tupleOf(first: A, second: B, third: C) = immutableTupleOf(first, second, third)

fun <A, B, C> immutableTupleOf(first: A, second: B, third: C) = ImmutableTuple(first, second, third)

fun <A, B, C> mutableTupleOf(first: A, second: B, third: C) = MutableTuple(first, second, third)

fun <A, B, C> Iterable<Tuple<A, B, C>>.forEachTuple(block: (first: A, second: B, third: C)->Unit) {
    forEach {
        block.invoke(it.first, it.second, it.third)
    }
}

// data class Tuple()
