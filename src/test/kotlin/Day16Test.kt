import Util.loadStringList
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day16Test {

    @Test
    fun part1_simple() {
        assertThat(Day16.part1(loadStringList("/day16_simple.txt")), `is`(71L))
    }

    @Test
    fun part1() {
        assertThat(Day16.part1(loadStringList("/day16_data.txt")), `is`(21978L))
    }

    @Test
    fun part2_simple() {
        val readInput = Day16.readInput(loadStringList("/day16_simple.txt"))
        assertThat(Day16.validFields(listOf(11, 40), readInput.rules), `is`(setOf("row")))
        assertThat(Day16.validFields(listOf(50, 40), readInput.rules), `is`(setOf("seat")))

    }

    @Test
    fun part2() {
        assertThat(Day16.part2(loadStringList("/day16_data.txt")), `is`(1053686852011L))
    }

}


