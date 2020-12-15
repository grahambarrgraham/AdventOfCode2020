/**
 * https://adventofcode.com/2020/day/15
 */
object Day15 {

    fun part1(string: String): Long = calc(string, 2020L)

    fun part2(string: String): Long = calc(string, 30000000L)

    fun calc(strings: String, target: Long): Long {

        val gameHistory = mutableMapOf<Long, MutableList<Long>>()

        val starters = strings.split(',')
                .map(String::trim)
                .map(String::toLong)
                .mapIndexed { index, startingNumber ->
                    gameHistory.store(startingNumber, index + 1L)
                }

        var lastVal = starters.last()
        val startingTurn = starters.size + 1

        (startingTurn..target).forEach { turn ->
            lastVal = gameHistory.store(calcNextVal(gameHistory, lastVal), turn)
        }

        return lastVal
    }

    private fun calcNextVal(gameHistory: MutableMap<Long, MutableList<Long>>, lastVal: Long): Long =
            if (gameHistory[lastVal]?.size == 1) {
                0L
            } else {
                val last2 = gameHistory[lastVal]!!.takeLast(2)
                last2.last() - last2.first()
            }

    private fun MutableMap<Long, MutableList<Long>>.store(key: Long, value: Long): Long {
        this.getOrPut(key) { mutableListOf() }.add(value)
        return key
    }

}
