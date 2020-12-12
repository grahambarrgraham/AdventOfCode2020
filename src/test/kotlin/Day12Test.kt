import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day12Test {

    @Test
    fun part1_simple() {
        assertThat(Day12.part1(listOf("N1", "S1", "E1", "W1", "R90", "L90", "F1", "R270", "L270", "F1", "L90", "F1", "L180", "F1", "L270", "F2" )), `is`(0L))
        assertThat(Day12.part1(loadStringListWithoutBlanks("/day12_simple.txt")), `is`(25L))
    }

    @Test
    fun part1() {
        assertThat(Day12.part1(loadStringListWithoutBlanks("/day12_data.txt")), `is`(415L))
    }

    @Test
    fun part2_simple() {
        assertThat(Day12.part2(listOf("F1")), `is`(11L))
        assertThat(Day12.part2(listOf("W9", "R180", "F1", "R180", "F2")), `is`(2L))
        assertThat(Day12.part2(loadStringListWithoutBlanks("/day12_simple.txt")), `is`(286L))
    }

    @Test
    fun part2() {
        assertThat(Day12.part2(loadStringListWithoutBlanks("/day12_data.txt")), `is`(29401L))
    }

}


