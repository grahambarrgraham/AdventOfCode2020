import Util.splitByBlankLine

/**
 * https://adventofcode.com/2020/day/6
 */
object Day6 {

    fun part1(strings: List<String>): Int = splitByBlankLine(strings).map(this::countPart1).sum()

    fun part2(strings: List<String>): Int = splitByBlankLine(strings).map(this::countPart2).sum()

    private fun countPart1(l: List<String>): Int = allAnsweredQuestions(l).count()

    private fun countPart2(l: List<String>): Int = allAnsweredQuestions(l)
            .map { wasAnsweredByEachPerson(l, it) }
            .filter { it }
            .count()

    private fun allAnsweredQuestions(l: List<String>): Set<Char> =
            l.flatMap { it.toCharArray().toSet() }.toSet()

    private fun wasAnsweredByEachPerson(l: List<String>, question: Char): Boolean =
            l.map { it.contains(question) }
                    .fold(true) { a, b -> a && b }
}
