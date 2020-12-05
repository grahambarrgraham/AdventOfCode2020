import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day5Test {

    @Test
    fun part1_simple() {
        assertThat(Day5.part1(loadStringListWithoutBlanks("/day5_simple.txt")), `is`(820))
    }

    @Test
    fun part1() {
        assertThat(Day5.part1(loadStringListWithoutBlanks("/day5_data.txt")), `is`(892))
    }

    @Test
    fun part2_simple() {
        assertThat(Day5.part2(loadStringListWithoutBlanks("/day5_simple.txt")), `is`(-1))
    }

    @Test
    fun part2() {
        assertThat(Day5.part2(loadStringListWithoutBlanks("/day5_data.txt")), `is`(-1))
    }

}


