import Util.loadIntList
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day10Test {

    @Test
    fun part1_simple() {
        assertThat(Day10.part1(loadIntList("/day10_simple.txt")), `is`(220))
        assertThat(Day10.part1(loadIntList("/day10_simple2.txt")), `is`(35))
    }

    @Test
    fun part1() {
        assertThat(Day10.part1(loadIntList("/day10_data.txt")), `is`(2040))
    }

    @Test
    fun part2_simple() {
        assertThat(Day10.part2Version2(listOf(1)), `is`(1L))
        assertThat(Day10.part2Version2(listOf(1, 2)), `is`(2L))
        assertThat(Day10.part2Version2(listOf(1, 2, 3)), `is`(4L))
        assertThat(Day10.part2Version2(listOf(1, 2, 3, 4)), `is`(7L))
        assertThat(Day10.part2Version2(loadIntList("/day10_simple2.txt")), `is`(8L))
        assertThat(Day10.part2Version2(loadIntList("/day10_simple.txt")), `is`(19208L))
        assertThat(Day10.part2Version1(loadIntList("/day10_data.txt")), `is`(28346956187648L))
    }

    @Test
    fun part2() {
        assertThat(Day10.part2Version2(loadIntList("/day10_data.txt")), `is`(28346956187648L))
        assertThat(Day10.part2Version1(loadIntList("/day10_data.txt")), `is`(28346956187648L))
    }

}


