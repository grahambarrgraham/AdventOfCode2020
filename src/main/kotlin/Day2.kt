/**
 * https://adventofcode.com/2020/day/2
 */
object Day2 {

    fun part1(strings: List<String>): Int = readPasswords(strings).filter { it: PasswordData -> it.isValidPart1() }.count()

    fun part2(strings: List<String>): Int = readPasswords(strings).filter { it: PasswordData -> it.isValidPart2() }.count()

    private fun readPasswords(strings: List<String>): List<PasswordData> = strings.map { it.parse() }

    private data class PasswordData(val a: Int, val b: Int, val letter: Char, val pw: String) {

        fun isValidPart1(): Boolean = pw.filter { it == letter }.count() in a..b

        fun isValidPart2(): Boolean = (pw.getOrNull(a - 1) == letter) xor
                (pw.getOrNull(b - 1) == letter)
    }

    private val pattern = Regex("(\\d+)-(\\d+) (.): (.+)")

    private fun String.parse(): PasswordData {
        val (min, max, letter, pw) = pattern.find(this)!!.destructured
        return PasswordData(min.toInt(), max.toInt(), letter[0], pw)
    }
}
