/**
 * https://adventofcode.com/2020/day/10
 */
object Day10 {

    fun part1(values: List<Int>): Int {

        val map = values
                .sorted()
                .mapIndexed { index, i -> if (index >= values.size - 1) 3 else values.sorted()[index + 1] - i }
                .groupBy { it }

        return (map[1]!!.count() + 1) * map[3]!!.count()
    }

    fun part2Version2(values: List<Int>): Long {

        val l = addEndpoints(values).sorted()

        val counts = mutableMapOf<Int, Long>().withDefault { 0L };
        counts[0] = 1L

        l.drop(1).forEach {
            val i = 3
            counts[it] = (counts[it - i] ?: 0) + (counts[it - 2] ?: 0) + (counts[it - 1] ?: 0)
        }

        return counts[values.max()!!]!!
    }

    fun part2Version1(values: List<Int>): Long {
        val l = addEndpoints(values).toList().sortedDescending()
        return generateCounts(l.first(), mutableMapOf(), createTree(l))
    }

    private fun createTree(l: List<Int>): Map<Int, List<Int>> = l
            .map { v -> Pair(v, l.filter { it -> (v - it) in 1..3 }) }
            .associate { it.first to it.second }


    private fun addEndpoints(values: List<Int>): MutableList<Int> {
        var p = values.toMutableList()
        p.add(0)
        p.add(values.max()!! + 3)
        return p
    }

    fun generateCounts(target: Int, memo: MutableMap<Int, Long>, prior: Map<Int, List<Int>>): Long {

        if (target == 0) {
            return 1
        }

        if (memo.containsKey(target)) {
            return memo[target]!!
        }

        memo[target] = prior[target]?.map { generateCounts(it, memo, prior) }?.sum() ?: 1

        return memo[target]!!
    }


}
