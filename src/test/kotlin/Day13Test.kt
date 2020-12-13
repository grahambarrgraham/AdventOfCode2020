import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day13Test {

    @Test
    fun part1_simple() {
        assertThat(Day13.part1(loadStringListWithoutBlanks("/day13_simple.txt")), `is`(295L))
    }

    @Test
    fun part1() {
        assertThat(Day13.part1(loadStringListWithoutBlanks("/day13_data.txt")), `is`(205L))
    }

    @Test
    fun part2_simple() {
        assertThat(Day13.part2(listOf("", "17,x,13,19")), `is`(3417L))
        assertThat(Day13.part2(listOf("", "67,7,59,61")), `is`(754018L))
        assertThat(Day13.part2(listOf("0", "67,x,7,59,61")), `is`(779210L))
        assertThat(Day13.part2(listOf("0", "67,7,x,59,61")), `is`(1261476L))
        assertThat(Day13.part2(listOf("0", "1789,37,47,1889")), `is`(1202161486L))
        assertThat(Day13.part2(loadStringListWithoutBlanks("/day13_simple.txt")), `is`(1068781L))
    }

    @Test
    fun part2() {
        assertThat(Day13.part2(loadStringListWithoutBlanks("/day13_data.txt")), `is`(-1L))
    }

}


