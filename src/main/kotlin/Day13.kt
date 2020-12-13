/**
 * https://adventofcode.com/2020/day/13
 */
object Day13 {

    fun part1(strings: List<String>): Long {

        val target = strings[0].toInt()

        val x = strings[1]
                .split(',')
                .filterNot { it == "x" }
                .map(String::toInt)
                .map { Pair(it, target - (target % it) + it) }
                .minBy { it.second }!!

        return x.first * (x.second - target).toLong()
    }

    fun part2(strings: List<String>): Long {

        val busList = strings[1]
                .split(',')
                .mapIndexed { index, it -> Pair(index, it) }
                .filterNot { it.second == "x" }
                .map { Bus(it.first, it.second.toLong()) }

        var step = 1L
        var current = busList.first().id

        for (bus in busList) {
            while(true) {
                if ((current + bus.minutesToWait) % bus.id == 0L) {
                    step *= bus.id
                    break;
                } else {
                    current += step
                }
            }
        }

        return current
    }
    
    data class Bus(val minutesToWait:Int, val id:Long)
}
