import Day14.applyMaskPart
import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day14Test {

    @Test
    fun part1_simple() {
        assertThat(Day14.part1(loadStringListWithoutBlanks("/day14_simple.txt")), `is`(165L))
    }

    @Test
    fun part1() {
        assertThat(Day14.part1(loadStringListWithoutBlanks("/day14_data.txt")), `is`(15919415426101L))
    }

    @Test
    fun part2_simple() {
        assertThat(Day14.part2(loadStringListWithoutBlanks("/day14_simple_part2.txt")), `is`(208L))
    }

    @Test
    fun part2() {
        assertThat(Day14.part2(loadStringListWithoutBlanks("/day14_data.txt")), `is`(3443997590975L))
    }

    @Test
    fun applyMask() {
        assertThat(applyMaskPart("000000000000000000000000000000X1001X", "000000000000000000000000000000101010", Day14::applyMaskPart2),
                `is`("000000000000000000000000000000X1101X"))

        assertThat(applyMaskPart("00000000000000000000000000000000X0XX", "000000000000000000000000000000011010", Day14::applyMaskPart2),
                `is`("00000000000000000000000000000001X0XX"))
    }

    @Test
    fun expandAddress() {

        val expected = listOf(
                "000000000000000000000000000000011010",
                "000000000000000000000000000000011011",
                "000000000000000000000000000000111010",
                "000000000000000000000000000000111011")

        assertThat(Day14.expandMask("000000000000000000000000000000X1101X").toList(), `is`(expected))

        val expected1 = listOf(
                "000000000000000000000000000000010000",
                "000000000000000000000000000000010001",
                "000000000000000000000000000000010010",
                "000000000000000000000000000000010011",
                "000000000000000000000000000000011000",
                "000000000000000000000000000000011001",
                "000000000000000000000000000000011010",
                "000000000000000000000000000000011011"
        )

        assertThat(Day14.expandMask("00000000000000000000000000000001X0XX").toList(), `is`(expected1))
    }
}


