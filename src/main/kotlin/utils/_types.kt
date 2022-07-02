package utils



typealias Matrix = Array<IntArray>

fun Matrix.toMatrixStr(print: Boolean = true) : String {
    val matrix = this

    var max = 0
    var maxBeginning = 0

    for (m in matrix)
        for (i in m.indices) {
            val n = m[i]
            if (n > max) max = n
            if (i == 0 && n > maxBeginning) maxBeginning = n
        }

    val maxLength = max.toString().length + 2
    val maxBeginningLength = maxBeginning.toString().length

    val str = StringBuilder().apply {
        append("\n")
        for (m in matrix) {
            append("[")
            for (i in m.indices) {
                if (i == 0) {
                    append(m[i].toString().padStart(maxBeginningLength))
                } else {
                    append(m[i].toString().padStart(maxLength))
                }
            }
            append("]\n")
        }
    }.toString()
    if (print) println(str)
    return str
}

fun Matrix.matrixEquals(other: Matrix) : Boolean {
    if (size != other.size) return false
    for (i in indices) {
        if (!get(i).contentEquals(other[i])) return false
    }
    return true
}

fun Matrix.deepClone() : Matrix = Matrix(size) {
    this[it].clone()
}