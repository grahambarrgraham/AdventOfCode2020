import Util.loadStringList
import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day20Test {

    private fun load(filename: String) = Util.splitByBlankLine(loadStringList(filename)).toList()

    @Test
    fun part1_simple() {
        assertThat(Day20.part1(load("/day20_simple.txt")), `is`(20899048083289L))
    }

    @Test
    fun part1() {
        assertThat(Day20.part1(load("/day20_data.txt")), `is`(0L))
    }

    @Test
    fun part2_simple() {
        assertThat(Day20.part2(load("/day20_simple.txt")), `is`(0L))
    }

    @Test
    fun part2() {
        assertThat(Day20.part2(load("/day20_data.txt")), `is`(0L))
    }

}


