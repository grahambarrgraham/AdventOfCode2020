/**
 * https://adventofcode.com/2020/day/16
 */
object Day16 {

    fun part1(strings: List<String>): Long {
        val input = readInput(strings)
        return input.nearbyTickets
                .flatten()
                .filterNot { fieldIsValidForAllRules(input.rules, it) }
                .sum()
    }

    fun part2(strings: List<String>): Long {
        return getFieldMap(strings)
                .filterKeys { it.startsWith("departure") }
                .values.fold(1L) { a, b -> a * b }
    }

    private fun getFieldMap(strings: List<String>): Map<String, Long> {
        val input = readInput(strings)
        val validTickets = findValidTickets(input)

        val map = mutableMapOf<String, Int>()

        (validTickets[0].indices)
                .mapIndexed { index, i ->
                    Pair(index, validFields(validTickets.map { ticket -> ticket[i] }, input.rules))
                }
                .sortedBy { it.second.size }
                .forEach {
                    val find = it.second.find { field -> !map.containsKey(field) }
                    map[find!!] = it.first
                }

        return map.mapValues { it -> input.yourTicket[it.value] }
    }

    private fun findValidTickets(input: Input): List<List<Long>> =
            input.nearbyTickets
                    .filter { ticket -> ticket.all { value -> fieldIsValidForAllRules(input.rules, value) } }

    fun readInput(strings: List<String>): Input {
        val sections = Util.splitByBlankLine(strings).toList()
        return Input(readFields(sections[0]), readTicket(sections[1][1]), sections[2].drop(1).map(this::readTicket))
    }

    private fun fieldIsValidForAllRules(rules: List<Rule>, value: Long): Boolean =
            rules.any { field -> fieldIsValidForAllRules(value, field) }

    private fun fieldIsValidForAllRules(value: Long, rule: Rule) =
            (value in rule.range1.first..rule.range1.second) || (value in rule.range2.first..rule.range2.second)

    private fun readTicket(s: String): List<Long> = s.split(',').map(String::toLong)

    val fieldRegex = "(.+?): (\\d+)\\-(\\d+) or (\\d+)\\-(\\d+)".toRegex()

    private fun readFields(list: List<String>): List<Rule> =
            list.map {
                val (name, r1a, r1b, r2a, r2b) = fieldRegex.find(it)!!.destructured
                Rule(name, Pair(r1a.toInt(), r1b.toInt()), Pair(r2a.toInt(), r2b.toInt()))
            }

    fun validFields(v: List<Long>, rules: List<Rule>): Set<String> =
            rules.filter { field -> areAllValid(v, field) }.map { it.name }.toSet()

    private fun areAllValid(v: List<Long>, rule: Rule) =
            v.map { fieldIsValidForAllRules(it, rule) }.fold(true) { a, b -> a && b }

    data class Rule(val name: String, val range1: Pair<Int, Int>, val range2: Pair<Int, Int>)

    data class Input(val rules: List<Rule>, val yourTicket: List<Long>, val nearbyTickets: List<List<Long>>)

}
