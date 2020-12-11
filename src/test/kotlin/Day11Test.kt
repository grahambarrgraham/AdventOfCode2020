import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day11Test {

    @Test
    fun part1_simple() {
        assertThat(Day11.part1(loadStringListWithoutBlanks("/day11_simple.txt")), `is`(37L))
    }

    @Test
    fun part1() {
        assertThat(Day11.part1(loadStringListWithoutBlanks("/day11_data.txt")), `is`(2152L))
    }

    @Test
    fun part2_simple() {
        assertThat(Day11.part2(loadStringListWithoutBlanks("/day11_simple.txt")), `is`(26L))
    }

    @Test
    fun part2() {
        assertThat(Day11.part2(loadStringListWithoutBlanks("/day11_data.txt")), `is`(1937L))
    }

}


