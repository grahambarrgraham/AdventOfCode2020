import org.apache.commons.math3.util.CombinatoricsUtils

/**
 * https://adventofcode.com/2020/day/1
 */
object Day1 {

    fun part1(target: Int, values: List<Int>): Int = calc(values, 2, target)

    fun part2(target: Int, values: List<Int>): Int = calc(values, 3, target)

    private fun calc(values: List<Int>, tupleSize: Int, target: Int): Int {
        return CombinatoricsUtils.combinationsIterator(values.size, tupleSize)
                .asSequence()
                .map { it.map { it2 -> values[it2] } }
                .find { it.sum() == target }
                ?.let { it -> it.fold(1) { a, b -> a * b } }
                ?: throw RuntimeException("Not found")
    }
}
