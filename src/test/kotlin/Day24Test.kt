import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day24Test {

    @Test
    fun part1_simple() {
        assertThat(Day24.part1(loadStringListWithoutBlanks("/day24_simple.txt")), `is`(10L))
    }

    @Test
    fun part1() {
        assertThat(Day24.part1(loadStringListWithoutBlanks("/day24_data.txt")), `is`(282L))
    }

    @Test
    fun part2_simple() {
        assertThat(Day24.part2(loadStringListWithoutBlanks("/day24_simple.txt")), `is`(2208L))
    }

    @Test
    fun part2() {
        assertThat(Day24.part2(loadStringListWithoutBlanks("/day24_data.txt")), `is`(3445L))
    }

}


