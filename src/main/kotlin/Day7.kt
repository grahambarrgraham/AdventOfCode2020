/**
 * https://adventofcode.com/2020/day/7
 */
object Day7 {

    fun part1(strings: List<String>): Int {
        return "shiny gold".findRoots(load(strings)).size
    }

    fun part2(strings: List<String>): Int {
        return "shiny gold".countBags(load(strings))
    }

    private fun String.countBags(parentMap: Map<String, List<Bag>>): Int {
        val count = parentMap[this]?.map { it.number }?.sum() ?: 0
        val map = parentMap[this]?.map { it.colour.countBags(parentMap) * it.number}?.sum() ?: 0
        val a = this
        println("$a count : $count parentCount : $map")
        return count + map
    }

    private fun String.findRoots(parentMap: Map<String, List<Bag>>): Set<String> {
        val map = parentMap.filter { it.value.map { it.colour }.contains(this) }.keys
        val parentSet = map.flatMap { it.findRoots(parentMap) }.toSet()
        return map + parentSet
    }

    private fun load(strings: List<String>): Map<String, List<Bag>> {
        return strings.map { it.parseTo() }.associate { it.first to it.second }
    }

    private val pattern = Regex("(.+) bags contain (.+)\\.")

    private val childPattern = Regex("(\\d+) (.+) bag(s*)")

    data class Bag(val colour: String, val number: Int)

    fun String.parseTo(): Pair<String, List<Bag>> {
        val (parentS, childrenS) = pattern.find(this)!!.destructured
        val children = childrenS.split(",").map { it.toBag() }.filterNotNull()
        return Pair(parentS.trim(), children)
    }

    fun String.toBag(): Bag? {
        if ("no other bags" == this) return null
        val (numS, name, ss) = childPattern.find(this)!!.destructured
        return Bag(name, numS.toInt())
    }

}
