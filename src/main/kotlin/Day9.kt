import org.apache.commons.math3.util.CombinatoricsUtils

/**
 * https://adventofcode.com/2020/day/9
 */
object Day9 {

    fun part1(values: List<Long>, preableSize: Int): Long =
            generate(values, preableSize)
                    .find { !it.validatePart1(2) }!!.target

    fun part2(values: List<Long>, target: Long): Long {
        val l = Check(values, target).validSublistForPart2().sorted()
        return l.first() + l.last()
    }

    private fun generate(l: List<Long>, preableSize: Int): Sequence<Check> = sequence {
        (0 until l.size - preableSize).forEach {
            yield(Check(l.subList(it, it + preableSize), l[preableSize + it]))
        }
    }

    data class Check(val values: List<Long>, val target: Long) {

        fun validatePart1(tupleSize: Int): Boolean =
                CombinatoricsUtils.combinationsIterator(values.size, tupleSize)
                        .asSequence()
                        .map { it.map { it2 -> values[it2] } }.toList()
                        .filter { it.toSet().size == tupleSize }.toList()
                        .any { it.sum() == target }

        fun validSublistForPart2(): List<Long> {
            (values.indices).forEach { start ->
                for (end in start + 2 until values.size) {
                    val sublist = values.subList(start, end)
                    if (sublist.sum() == target) return sublist
                    if (sublist.sum() > target) break
                }
            }
            return emptyList()
        }

    }
}
