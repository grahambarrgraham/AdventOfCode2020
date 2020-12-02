import org.apache.commons.math3.util.CombinatoricsUtils

/**
 * https://adventofcode.com/2020/day/1
 */
object Day1 {

    fun part1V1(target: Int, values: List<Int>): Int {

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

    fun part1V2(target: Int, values: List<Int>): Int = calc(values, 2, target)

    fun part2(target: Int, values: List<Int>): Int = calc(values, 3, target)

    private fun calc(values: List<Int>, tupleSize: Int, target: Int): Int {
        return CombinatoricsUtils.combinationsIterator(values.size, tupleSize)
                .asSequence()
                .map { it -> it.map { it2 -> values[it2] } }
                .find { it -> it.sum() == target }
                ?.let { it -> it.fold(1) { a, b -> a * b } }
                ?: throw RuntimeException("Not found")
    }
}
