/**
 * https://adventofcode.com/2020/day/11
 */
object Day11 {

    fun part1(strings: List<String>): Long =
            execute(SeatingMap(strings)) { coord, seatingMap -> seatingMap.nextStatePart1(coord) }

    fun part2(strings: List<String>): Long =
            execute(SeatingMap(strings)) { coord, seatingMap -> seatingMap.nextStatePart2(coord) }

    private fun execute(map: SeatingMap, previousMap: SeatingMap? = null, transform: (Coord, SeatingMap) -> Char): Long =
            when (map) {
                previousMap -> map.countOccupiedSeats().toLong()
                else -> {
                    execute(map.evolveMap(transform), map, transform)
                }
            }

    private data class Coord(val x: Int, val y: Int)

    private data class SeatingMap(val map: List<String>) {

        fun nextStatePart1(coord: Coord): Char = when (seatState(coord)) {
            'L' -> if (adjacentOccupiedSeats(coord).isEmpty()) '#' else 'L'
            '#' -> if (adjacentOccupiedSeats(coord).size > 3) 'L' else '#'
            else -> '.'
        }

        fun nextStatePart2(coord: Coord): Char = when (seatState(coord)) {
            'L' -> if (visibleOccupiedSeats(coord).isEmpty()) '#' else 'L'
            '#' -> if (visibleOccupiedSeats(coord).size > 4) 'L' else '#'
            else -> '.'
        }

        fun seatState(coord: Coord): Char = if (isOnGrid(coord)) map[coord.y][coord.x] else '.'

        fun adjacentOccupiedSeats(coord: Coord) = neighbours.map { it(coord) }.filter { seatState(it) == '#' }

        fun visibleOccupiedSeats(coord: Coord) = neighbours.mapNotNull { existsVisibleOccupiedSeat(coord, it) }

        private fun existsVisibleOccupiedSeat(start: Coord, next: (Coord) -> Coord): Coord? {
            val nextCoord = next(start)
            return when {
                !isOnGrid(nextCoord) -> null
                seatState(nextCoord) == 'L' -> null
                seatState(nextCoord) == '#' -> nextCoord
                else -> existsVisibleOccupiedSeat(nextCoord, next)
            }
        }

        private fun isOnGrid(coord: Coord) = coord.x in map[0].indices && coord.y in map.indices

        fun evolveMap(transform: (Coord, SeatingMap) -> Char): SeatingMap =
                SeatingMap(map.mapIndexed { y, it -> evolveRow(it, y, transform) })

        private fun evolveRow(row: String, y: Int, transform: (Coord, SeatingMap) -> Char) =
                row.mapIndexed { x, _ -> transform(Coord(x, y), this) }.joinToString("")

        fun countOccupiedSeats() = map.map { it.count { c -> c == '#' } }.sum()
    }

    private val neighbours = listOf(
            { it -> Coord(it.x, it.y + 1) },
            { it -> Coord(it.x, it.y - 1) },
            { it -> Coord(it.x + 1, it.y + 1) },
            { it -> Coord(it.x + 1, it.y) },
            { it -> Coord(it.x + 1, it.y - 1) },
            { it -> Coord(it.x - 1, it.y + 1) },
            { it -> Coord(it.x - 1, it.y - 1) },
            { it: Coord -> Coord(it.x - 1, it.y) })


}
