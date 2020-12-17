import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day17Test {

    @Test
    fun part1_simple() {
        assertThat(Day17.part1(loadStringListWithoutBlanks("/day17_simple.txt")), `is`(112L))
    }

    @Test
    fun part1() {
        assertThat(Day17.part1(loadStringListWithoutBlanks("/day17_data.txt")), `is`(324L))
    }

    @Test
    fun part2() {
        assertThat(Day17.part2(loadStringListWithoutBlanks("/day17_data.txt")), `is`(1836L))
    }

}


