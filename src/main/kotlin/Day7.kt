/**
 * https://adventofcode.com/2020/day/7
 */
object Day7 {

    fun part1(strings: List<String>): Int = "shiny gold".findRoots(load(strings)).size

    fun part2(strings: List<String>): Int = "shiny gold".countBags(load(strings))

    private fun String.countBags(parentMap: Map<String, List<Bag>>): Int {
        val count = parentMap[this]
                ?.map { it.number }
                ?.sum()
                ?: 0

        val parentCount = parentMap[this]
                ?.map { it.bagType.countBags(parentMap) * it.number }
                ?.sum()
                ?: 0

        return count + parentCount
    }

    private fun String.findRoots(parentMap: Map<String, List<Bag>>): Set<String> {
        val directlyContained = parentMap
                .filter { it.value.map { bag -> bag.bagType }.contains(this) }
                .keys

        val indirectlyContained = directlyContained
                .flatMap { it.findRoots(parentMap) }
                .toSet()

        return directlyContained + indirectlyContained
    }

    private fun load(strings: List<String>): Map<String, List<Bag>> =
            strings
                    .mapNotNull { it.parseLine() }
                    .associate { it.first to it.second }

    private val linePattern = Regex("(.+) bags contain (.+)\\.")

    private val bagPattern = Regex("(\\d+) (.+) bag(s*)")

    data class Bag(val bagType: String, val number: Int)

    private fun String.parseLine(): Pair<String, List<Bag>>? {
        return linePattern.find(this)?.let { line ->
            val (parentS, childrenS) = line.destructured
            return Pair(parentS, parseChildren(childrenS))
        }
    }

    private fun parseChildren(childrenS: String) =
            childrenS
                    .split(",")
                    .mapNotNull { it.parseBag() }

    private fun String.parseBag(): Bag? {
        return bagPattern.find(this)?.let {
            val (numS, name, _) = it.destructured
            return Bag(name, numS.toInt())
        }
    }

}
