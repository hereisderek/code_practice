package utils


// Generic<String>().checkType("foo")
// Generic<String>().checkType(1)
class Generic<T : Any>(val klass: Class<T>) {
    companion object {
        inline operator fun <reified T : Any>invoke() = Generic(T::class.java)
    }

    fun isInstance(t: Any) = klass.isAssignableFrom(t.javaClass)
}
