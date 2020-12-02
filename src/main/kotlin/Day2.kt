/**
 * https://adventofcode.com/2020/day/2
 */
object Day2 {

    data class Data(val a: Int, val b: Int, val letter: Char, val pw: String) {
        fun isValidPart1(): Boolean = pw.filter { it == letter }.count() in a..b
        fun isValidPart2(): Boolean = (pw.getOrNull(a - 1) == letter) xor (pw.getOrNull(b - 1) == letter)
    }

    private val pattern = Regex("(\\d+)-(\\d+) (.): (.+)")

    private fun String.parse(): Data {
        val a = pattern.find(this)!!
        val (min, max, letter, pw) = a.destructured
        return Data(min.toInt(), max.toInt(), letter[0], pw)
    }

    fun part1(strings: List<String>): Int = strings.map { it.parse() }.filter { it.isValidPart1() }.count()

    fun part2(strings: List<String>): Int = strings.map { it.parse() }.filter { it.isValidPart2() }.count()

}
