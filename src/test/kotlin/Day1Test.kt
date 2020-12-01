import Util.loadIntList
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class Day1Test {

    @Test
    fun part1_simple() {
        assertThat(Day1.part1V1(2020, listOf(1721, 232, 299, 1232, 434, 4341, 1)), `is`(514579))
    }

    @Test
    fun part1_v1() {
        assertThat(Day1.part1V1(2020, loadIntList("/day1_data.txt")), `is`(1018944))
    }

    @Test
    fun part1_v2() {
        assertThat(Day1.part1V2(2020, loadIntList("/day1_data.txt")), `is`(1018944))
    }

    @Test
    fun part1_v3() {
        assertThat(Day1.part1V3(2020, loadIntList("/day1_data.txt")), `is`(1018944))
    }

    @Test
    fun part2_simple() {
        assertThat(Day1.part2(2020, listOf(1721, 675, 232, 299, 366, 1232, 434, 4341, 1, 979)), `is`(241861950))
    }

    @Test
    fun part2() {
        assertThat(Day1.part2(2020, loadIntList("/day1_data.txt")), `is`(8446464))
    }

}


