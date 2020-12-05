/**
 * https://adventofcode.com/2020/day/n
 */
object Day5 {

    fun part1(strings: List<String>): Int = strings.map(this::score).max()!!

    fun part2(strings: List<String>): Int  {

        val manifest = strings.map(this::score).sorted()

        return manifest
                .filterIndexed { index, i -> findGap(index, manifest, i) }
                .map { it + 1 }
                .first()

    }

    private fun findGap(index: Int, sorted: List<Int>, i: Int) =
            index + 1 < sorted.size && sorted[index + 1] - i > 1

    fun score(s: String): Int {
        val (rowS, colS) = s.partition { it == 'B' || it == 'F' }
        val row = getInt(rowS, 'B')
        val col = getInt(colS, 'R')
        return (row * 8) + col;
    }

    private fun getInt(rowS: String, upper: Char) =
            String(rowS.map { if (it == upper) '1' else '0' }.toCharArray()).toInt(2)

}
