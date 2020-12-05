/**
 * https://adventofcode.com/2020/day/5
 */
object Day5 {

    fun part1(strings: List<String>): Int = strings.map(this::seatNumber).max()!!

    fun part2(strings: List<String>): Int {

        val manifest = strings.map(this::seatNumber).sorted()

        return manifest
                .filterIndexed { index, i -> nextSeatIsVacant(index, manifest, i) }
                .map { it + 1 }
                .first()
    }

    private fun nextSeatIsVacant(index: Int, manifest: List<Int>, seatNumber: Int) =
            index + 1 < manifest.size && manifest[index + 1] - seatNumber > 1

    private fun seatNumber(s: String): Int {
        val (rowS, colS) = s.partition { it == 'B' || it == 'F' }
        val row = int(rowS, binary('B'))
        val col = int(colS, binary('R'))
        return (row * 8) + col;
    }

    private fun int(rowS: String, transform: (Char) -> Char): Int =
            String(rowS.map(transform).toCharArray()).toInt(2)

    private fun binary(upper: Char): (Char) -> Char = { if (it == upper) '1' else '0' }

}
