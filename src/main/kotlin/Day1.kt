/**
 * https://adventofcode.com/2020/day/1
 */
object Day1 {

    fun answer1(target: Int, values: List<Int>): Int {

        fun loop(n: Int): Int {

            if (n >= values.size) throw RuntimeException("target not found")

            val i = values[n]

            values.drop(n).forEach {
                if (i + it == target) return i * it
            }

            return loop(n + 1)
        }

        return loop(0)
    }

    fun answer2(target: Int, values: List<Int>): Int =
        values.cartesianProduct(values).find { it.first + it.second == target }?.let { it.first * it.second }
                ?: throw RuntimeException("Not found")

    fun <S, T> List<S>.cartesianProduct(other: List<T>) = this.flatMap {
        List(other.size) { i -> Pair(it, other[i]) }
    }
}
