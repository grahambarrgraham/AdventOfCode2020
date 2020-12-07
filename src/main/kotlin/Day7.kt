/**
 * https://adventofcode.com/2020/day/7
 */
object Day7 {

    fun part1(strings: List<String>): Int = "shiny gold".findRoots(load(strings)).size

    fun part2(strings: List<String>): Int = "shiny gold".countBags(load(strings))

    private fun String.countBags(parentMap: Map<String, List<Bag>>): Int {
        val count = parentMap[this]?.map { it.number }?.sum() ?: 0
        val parentCount = parentMap[this]?.map { it.bagType.countBags(parentMap) * it.number}?.sum() ?: 0
        return count + parentCount
    }

    private fun String.findRoots(parentMap: Map<String, List<Bag>>): Set<String> {
        val directlyContained = parentMap.filter { it.value.map { bag -> bag.bagType }.contains(this) }.keys
        val indirectlyContained = directlyContained.flatMap { it.findRoots(parentMap) }.toSet()
        return directlyContained + indirectlyContained
    }

    private fun load(strings: List<String>): Map<String, List<Bag>> = strings.map { it.parseLine() }.associate { it.first to it.second }

    private val linePattern = Regex("(.+) bags contain (.+)\\.")

    private val bagPattern = Regex("(\\d+) (.+) bag(s*)")

    data class Bag(val bagType: String, val number: Int)

    fun String.parseLine(): Pair<String, List<Bag>> {
        val (parentS, childrenS) = linePattern.find(this)!!.destructured
        val children = childrenS.split(",").map { it.parseBag() }.filterNotNull()
        return Pair(parentS.trim(), children)
    }

    fun String.parseBag(): Bag? {
        if ("no other bags" == this) return null
        val (numS, name, _) = bagPattern.find(this)!!.destructured
        return Bag(name, numS.toInt())
    }

}
