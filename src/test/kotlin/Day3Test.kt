import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day3Test {

    @Test
    fun part1_simple() {
        assertThat(Day3.part1(loadStringListWithoutBlanks("/day3_simple.txt")), `is`(7))
        assertThat(Day3.calc(loadStringListWithoutBlanks("/day3_simple.txt"), 1, 2), `is`(2))
        assertThat(Day3.calc(loadStringListWithoutBlanks("/day3_simple.txt"), 7, 1), `is`(4))
    }

    @Test
    fun part1() {
        assertThat(Day3.part1(loadStringListWithoutBlanks("/day3_data.txt")), `is`(250))
    }

    @Test
    fun part2_simple() {
        assertThat(Day3.part2(loadStringListWithoutBlanks("/day3_simple.txt")), `is`(336))
    }

    @Test
    fun part2() {
        assertThat(Day3.part2(loadStringListWithoutBlanks("/day3_data.txt")), `is`(1592662500))
    }

}


