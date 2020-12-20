import Util.loadStringList
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day19Test {

    @Test
    fun part1_simple() {
        assertThat(Day19.eval(load("/day19_simple.txt")), `is`(2L))
    }

    private fun load(filename: String) = Util.splitByBlankLine(loadStringList(filename)).toList()

    @Test
    fun part1() {
        assertThat(Day19.eval(load("/day19_data.txt")), `is`(213L))
    }

    @Test
    fun part2_simple() {
        assertThat(Day19.eval(load("/day19_part2_a.txt")), `is`(3L))
        assertThat(Day19.eval(load("/day19_part2_b.txt")), `is`(12L))
    }

    @Test
    fun part2() {
        assertThat(Day19.eval(load("/day19_data_part2.txt")), `is`(325L))
    }

}


