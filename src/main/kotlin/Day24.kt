import Day24.Direction.*
import java.util.*

/**
 * https://adventofcode.com/2020/day/24
 */
object Day24 {

    fun part1(strings: List<String>): Long = eval(strings, 0)

    fun part2(strings: List<String>): Long = eval(strings, 100)

    private fun eval(strings: List<String>, times: Int): Long {

        val map = applyRules(strings)

        repeat(times) {
            (findBlacksToFlip(map) + findWhitesToFlip(map)).map { it.flip() }
        }

        return map.values
                .filterNot { it.isWhite }
                .count().toLong()
    }

    private fun findBlacksToFlip(map: MutableMap<Point, Hex>): Set<Hex> =
            map
                    .filterNot { e -> e.value.isWhite }
                    .filter { val c = countBlackNeighbours(it.key, map); c == 0 || c > 2 }
                    .map { it -> it.value }
                    .toSet()

    private fun countBlackNeighbours(point: Point, map: MutableMap<Point, Hex>): Int =
            Direction.values()
                    .map { point.go(it) }
                    .mapNotNull { map[it] }
                    .filterNot { it.isWhite }
                    .count()

    private fun findWhitesToFlip(map: MutableMap<Point, Hex>): Set<Hex> =
            findCandidateWhites(map)
                    .filter { countBlackNeighbours(it.first, map) == 2 }
                    .map { it.second }
                    .toSet()

    private fun findCandidateWhites(map: MutableMap<Point, Hex>): List<Pair<Point, Hex>> {
        return map
                .filterNot { e -> e.value.isWhite }
                .flatMap { e -> values().map { e.key.go(it) }
                }.map {
                    if (map[it] == null) {
                        map[it] = Hex()
                    }
                    Pair(it, map[it]!!)
                }.filter {
                    it.second.isWhite
                }
    }

    private fun applyRules(strings: List<String>): MutableMap<Point, Hex> {
        val map = mutableMapOf<Point, Hex>()
        map[Point(0, 0)] = Hex()

        val input: List<List<Direction>> = strings.map { readDirections(it) }

        input.forEach {
            val location = followDirections(it)
            map.putIfAbsent(location, Hex())
            map.computeIfPresent(location) { _, hex -> hex.flip() }
        }
        return map
    }

    private fun followDirections(it: List<Direction>): Point =
            it.fold(Point(0, 0)) { acc, direction -> acc.go(direction) }

    private fun readDirections(it: String): List<Direction> {
        var register: Char? = null
        val result = mutableListOf<Direction>()
        it.forEach {
            when (Pair(it, register)) {
                Pair('e', null) -> result.add(E)
                Pair('w', null) -> result.add(W)
                Pair('w', 'n') -> {
                    result.add(NW); register = null
                }
                Pair('e', 'n') -> {
                    result.add(NE); register = null
                }
                Pair('w', 's') -> {
                    result.add(SW); register = null
                }
                Pair('e', 's') -> {
                    result.add(SE); register = null
                }
                Pair('n', null) -> {
                    register = 'n'
                }
                Pair('s', null) -> {
                    register = 's'
                }
                else -> throw RuntimeException("Unexpected $it $register")
            }
        }
        return result
    }

    data class Point(val x: Int, val y: Int) {
        fun go(direction: Direction) = when (direction) {
            NE -> Point(x, y + 1)
            SE -> Point(x + 1, y - 1)
            SW -> Point(x, y - 1)
            NW -> Point(x - 1, y + 1)
            W -> Point(x - 1, y)
            E -> Point(x + 1, y)
        }
    }

    data class Hex(val id: UUID = UUID.randomUUID()) {
        var isWhite: Boolean = true
        fun flip(): Hex {
            isWhite = !isWhite
            return this
        }
    }

    enum class Direction {
        NE, NW, SE, SW, W, E
    }

}
