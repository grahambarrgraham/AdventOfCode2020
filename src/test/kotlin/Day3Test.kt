import Util.loadStringList
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day3Test {

    @Test
    fun part1_simple() {
        assertThat(Day3.part1(loadStringList("/day3_simple.txt")), `is`(0))
    }

    @Test
    fun part1() {
        assertThat(Day3.part1(loadStringList("/day3_data.txt")), `is`(0))
    }

    @Test
    fun part2_simple() {
        assertThat(Day3.part2(loadStringList("/day3_simple.txt")), `is`(0))
    }

    @Test
    fun part2() {
        assertThat(Day3.part2(loadStringList("/day3_data.txt")), `is`(0))
    }

}


