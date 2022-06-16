package utils

interface Tuple<A, B, C> {
    val first: A
    val second: B
    val third: C
}


class ImmutableTuple<A, B, C>(
    override val first: A,
    override val second: B,
    override val third: C,
) : Tuple<A, B, C>

class MutableTuple<A, B, C>(
    override var first: A,
    override var second: B,
    override var third: C,
) : Tuple<A, B, C>

fun <A, B, C> immutableTupleOf(first: A, second: B, third: C) = ImmutableTuple(first, second, third)

fun <A, B, C> mutableTupleOf(first: A, second: B, third: C) = MutableTuple(first, second, third)



// data class Tuple()
