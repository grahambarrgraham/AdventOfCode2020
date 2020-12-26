import Util.loadStringList
import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day22Test {

    private fun load(filename: String) = Util.splitByBlankLine(loadStringList(filename)).toList()

    @Test
    fun part1_simple() {
        assertThat(Day22.part1(load("/day22_simple.txt")), `is`(306L))
    }

    @Test
    fun part1() {
        assertThat(Day22.part1(load("/day22_data.txt")), `is`(32199L))
    }

    @Test
    fun part2_simple() {
        assertThat(Day22.part2(load("/day22_part2_infinite.txt")), `is`(105L))
        assertThat(Day22.part2(load("/day22_simple.txt")), `is`(291L))
    }

    @Test
    fun part2() {
        assertThat(Day22.part2(load("/day22_data.txt")), `is`(33780L))
    }

}


