/**
 * https://adventofcode.com/2020/day/4
 */
object Day4 {

    val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    data class Passport(val codes: Map<String, String>) {

        fun isValidPart1() = requiredFields
                .map { codes.containsKey(it) }
                .fold(true) { a, b -> a && b }

        fun isValidPart2() =
                isValidPart1() &&
                        codes["byr"]?.toInt() in 1920..2002 &&
                        codes["iyr"]?.toInt() in 2010..2020 &&
                        codes["eyr"]?.toInt() in 2020..2030 &&
                        validHeight(codes["hgt"]!!) &&
                        "#[0-9|a-f]{6}".toRegex().matches(codes["hcl"]!!) &&
                        listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(codes["ecl"]) &&
                        "[0-9]{9}".toRegex().matches(codes["pid"]!!)

        private fun validHeight(s: String): Boolean {
            val (num, unit) = "(\\d+)\\s*(\\w+)".toRegex().find(s)!!.destructured
            return if (unit.trim().endsWith("cm")) {
                num.toInt() in 150..193
            } else if (unit.trim().endsWith("in")) {
                num.toInt() in 59..76
            } else {
                false
            }
        }
    }

    fun part1(strings: List<String>): Int = read(strings).filter { it.isValidPart1() }.count()

    fun part2(strings: List<String>): Int = read(strings).filter { it.isValidPart2() }.count()

    fun read(strings: List<String>): List<Passport> =
            splitByBlankLine(strings).map { Passport(codes(it)) }.toList()

    fun splitByBlankLine(strings: List<String>) = sequence {

        var i = 0;

        while (i < strings.size) {
            val list = strings.subList(i, strings.size).takeWhile { it.isNotBlank() }
            yield(list)
            i += list.size + 1
        }
    }

    fun codes(lines: List<String>): Map<String, String> = lines
            .map { it.split("\\s+".toRegex()) }
            .flatten().map { it.trim() }
            .associate { val a = it.split(":"); a[0] to a[1] }

}
