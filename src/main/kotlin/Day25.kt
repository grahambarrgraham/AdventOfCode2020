/**
 * https://adventofcode.com/2020/day/25
 */
object Day25 {

    fun part1(strings: List<String>): Long {
        val publicKey1 = strings[0].toLong()
        val publicKey2 = strings[1].toLong()
        val subjectNumber = 7L

        var key1 = 1L
        var key2 = 1L

        var loopCounter = 1

        fun transform(value: Long, subjectNumber: Long) = (value * subjectNumber) % 20201227

        while(true) {
            key1 = transform(key1, subjectNumber)
            if (key1 == publicKey1) break
            loopCounter++
        }

        repeat(loopCounter) {
            key2 = transform(key2, publicKey2)
        }

        return key2
    }

    fun part2(strings: List<String>): Long = 0

}
