import kotlin.math.pow

/**
 * https://adventofcode.com/2020/day/17
 */
object Day17 {

    fun part1(strings: List<String>) = eval(load(strings))

    fun part2(strings: List<String>) = eval(load(strings).map { it.addDimension() }.toSet())

    private fun load(strings: List<String>): Set<Coord> =
            strings.mapIndexed { y, s -> s.mapIndexedNotNull { x, c -> if (c == '#') Coord(listOf(x, y, 0)) else null } }
                    .flatten().toSet()

    data class Coord(val values: List<Int>) {
        fun addDimension(): Coord = Coord(values + 0)
    }

    private fun eval(input: Set<Coord>): Long {
        val dim = input.first().values.size

        var cube = ConwayCube(input)

        repeat(6) {
            cube = cube.eval(dim)
        }

        return cube.countPoints()
    }

    private data class ConwayCube(val points: Set<Coord>) {

        fun eval(dimensions: Int): ConwayCube =
                ConwayCube(countNeighbours(points, dimensions)
                        .filter { (p, c) -> (p in points && c in 2..3) || (p !in points && c == 3) }
                        .map { it.key }
                        .toSet())

        private fun countNeighbours(points: Set<Coord>, dim: Int): Map<Coord, Int> =

                /**
                 * thanks to https://github.com/nielsutrecht/adventofcode/blob/master/src/main/kotlin/com/nibado/projects/advent/y2020/Day17.kt, for the
                 * nice folding to map trick
                 */
                points
                        .flatMap { neighbours(it.values, dim) }
                        .fold(mutableMapOf<List<Int>, Int>()) { map, p ->
                            map[p] = map.getOrDefault(p, 0) + 1
                            map
                        }.mapKeys { Coord(it.key) }


        fun countPoints() = points.size.toLong()
    }

    private fun neighbours(list: List<Int>, dim: Int) = neighbours(dim).map { n -> list.mapIndexed { i, v -> n[i] + v } }

    private fun neighbours(dim: Int) =
            (0 until 3f.pow(dim).toInt())
                    .map { radix3Int(it, dim).map { r3 -> r3 - 1 } }
                    .filterNot { it.all { it == 0 } }

    private fun radix3Int(v: Int, dim: Int): List<Int> {
        return v.toString(3)
                .padStart(dim, '0')
                .toCharArray()
                .toList()
                .map { it.toString().toInt() }
    }

}
