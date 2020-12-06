import Util.loadStringList
import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day6Test {

    @Test
    fun part1_simple() {
        assertThat(Day6.part1(loadStringList("/day6_simple.txt")), `is`(11))
    }

    @Test
    fun part1() {
        assertThat(Day6.part1(loadStringList("/day6_data.txt")), `is`(6748))
    }

    @Test
    fun part2_simple() {
        assertThat(Day6.part2(loadStringList("/day6_simple.txt")), `is`(6))
    }

    @Test
    fun part2() {
        assertThat(Day6.part2(loadStringList("/day6_data.txt")), `is`(3445))
    }

}


