/**
 * https://adventofcode.com/2020/day/3
 */
object Day3 {

    class Map(val map: List<String>) {
        val width = map[0].length
        var currentX = 0
        var currentY = 0

        fun isFinished() = currentY >= map.size

        fun isTreeHere(): Boolean = map[currentY][currentX % width] == '#'

        fun next(right: Int, down: Int) {
            currentX += right
            currentY += down
        }
    }

    fun part1(strings: List<String>): Int =
            calc(strings, 3, 1)

    fun part2(strings: List<String>): Int =
            calc(strings, 1, 1) *
                    calc(strings, 3, 1) *
                    calc(strings, 5, 1) *
                    calc(strings, 7, 1) *
                    calc(strings, 1, 2)

    fun calc(strings: List<String>, right: Int, down: Int): Int {
        val map = Map(strings)
        var count = 0

        while (!map.isFinished()) {
            if (map.isTreeHere()) count++
            map.next(right, down)
        }

        return count
    }


}
