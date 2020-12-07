import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day7Test {

    @Test
    fun part1_simple() {
        assertThat(Day7.part1(loadStringListWithoutBlanks("/day7_simple.txt")), `is`(4))
    }

    @Test
    fun part1() {
        assertThat(Day7.part1(loadStringListWithoutBlanks("/day7_data.txt")), `is`(268))
    }

    @Test
    fun part2_simple() {
        assertThat(Day7.part2(loadStringListWithoutBlanks("/day7_simple.txt")), `is`(32))
    }

    @Test
    fun part2_simple2() {
        assertThat(Day7.part2(loadStringListWithoutBlanks("/day7_part2_simple.txt")), `is`(126))
    }

    @Test
    fun part2() {
        assertThat(Day7.part2(loadStringListWithoutBlanks("/day7_data.txt")), `is`(7867))
    }

}


