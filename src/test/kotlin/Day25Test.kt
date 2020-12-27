import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day25Test {

    @Test
    fun part1_simple() {
        assertThat(Day25.part1(loadStringListWithoutBlanks("/day25_simple.txt")), `is`(14897079L))
    }

    @Test
    fun part1() {
        assertThat(Day25.part1(loadStringListWithoutBlanks("/day25_data.txt")), `is`(12181021L))
    }

    @Test
    fun part2_simple() {
        assertThat(Day25.part2(loadStringListWithoutBlanks("/day25_simple.txt")), `is`(0L))
    }

    @Test
    fun part2() {
        assertThat(Day25.part2(loadStringListWithoutBlanks("/day25_data.txt")), `is`(0L))
    }

}


