import Util.loadIntList
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class Day1Test {

    @Test
    fun part1_simple() {
        assertThat(Day1.part1(listOf(1721, 232, 299, 1232, 434, 4341, 1)), `is`(514579))
    }

    @Test
    fun part1() {
        assertThat(Day1.part1(loadIntList("/day1_data.txt")), `is`(1018944))
    }

    @Test
    fun part2_simple() {
        assertThat(Day1.part2(listOf(1721, 675, 232, 299, 366, 1232, 434, 4341, 1, 979)), `is`(241861950))
    }

    @Test
    fun part2() {
        assertThat(Day1.part2(loadIntList("/day1_data.txt")), `is`(8446464))
    }

}


