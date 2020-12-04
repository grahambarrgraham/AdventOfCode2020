import Util.splitByBlankLine

/**
 * https://adventofcode.com/2020/day/4
 */
object Day4 {

    data class Passport(val codes: Map<String, String>) {

        fun isValidPart1() = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
                .map { codes.containsKey(it) }
                .fold(true) { a, b -> a && b }

        fun isValidPart2() =
                isValidPart1() &&
                        codes["byr"]?.toInt() in 1920..2002 &&
                        codes["iyr"]?.toInt() in 2010..2020 &&
                        codes["eyr"]?.toInt() in 2020..2030 &&
                        match("(1[5-8]\\d|19[0-3])cm|(59|6\\d|7[0-6])in", "hgt") &&
                        match("#[0-9|a-f]{6}", "hcl") &&
                        match("amb|blu|brn|gry|grn|hzl|oth", "ecl") &&
                        match("[0-9]{9}", "pid")


        fun match(regexp: String, key: String) = regexp.toRegex().matches(codes[key]!!)
    }

    fun part1(strings: List<String>) = read(strings).filter { it.isValidPart1() }.count()

    fun part2(strings: List<String>) = read(strings).filter { it.isValidPart2() }.count()

    fun read(strings: List<String>) = splitByBlankLine(strings).map { Passport(codes(it)) }.toList()

    fun codes(lines: List<String>) =
            lines
                    .flatMap { it.split("\\s+".toRegex()) }
                    .associate { val kv = it.split(":"); kv[0] to kv[1] }

}
