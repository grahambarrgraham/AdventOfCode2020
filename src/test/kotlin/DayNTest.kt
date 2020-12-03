import Util.loadStringList
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class DayNTest {

    @Test
    fun part1_simple() {
        assertThat(DayN.part1(loadStringList("/dayN_simple.txt")), `is`(0))
    }

    @Test
    fun part1() {
        assertThat(DayN.part1(loadStringList("/dayN_data.txt")), `is`(0))
    }

    @Test
    fun part2_simple() {
        assertThat(DayN.part2(loadStringList("/dayN_simple.txt")), `is`(0))
    }

    @Test
    fun part2() {
        assertThat(DayN.part2(loadStringList("/dayN_data.txt")), `is`(0))
    }

}


