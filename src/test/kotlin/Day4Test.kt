import Util.loadStringList
import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day4Test {

    @Test
    fun part1_simple() {
        assertThat(Day4.part1(loadStringList("/day4_simple.txt")), `is`(2))
    }

    @Test
    fun part1() {
        assertThat(Day4.part1(loadStringList("/day4_data.txt")), `is`(216))
    }

    @Test
    fun part2_invalid() {
        assertThat(Day4.part2(loadStringList("/day4_part2invalid.txt")), `is`(0))
    }

    @Test
    fun part2_valid() {
        assertThat(Day4.part2(loadStringList("/day4_part2valid.txt")), `is`(4))
    }

    @Test
    fun part2() {
        assertThat(Day4.part2(loadStringList("/day4_data.txt")), `is`(150))
    }

}


