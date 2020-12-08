import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day8Test {

    @Test
    fun part1_simple() {
        assertThat(Day8.part1(loadStringListWithoutBlanks("/day8_simple.txt")), `is`(5))
    }

    @Test
    fun part1() {
        assertThat(Day8.part1(loadStringListWithoutBlanks("/day8_data.txt")), `is`(1744))
    }

    @Test
    fun part2_simple() {
        assertThat(Day8.part2(loadStringListWithoutBlanks("/day8_simple.txt")), `is`(8))
    }

    @Test
    fun part2() {
        assertThat(Day8.part2(loadStringListWithoutBlanks("/day8_data.txt")), `is`(1174))
    }

}


