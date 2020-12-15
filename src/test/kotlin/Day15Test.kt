import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Ignore
import org.junit.Test

class Day15Test {

    @Test
    fun part1_simple() {
        assertThat(Day15.part1("0,3,6"), `is`(436L))
        assertThat(Day15.part1("1,3,2"), `is`(1L))
        assertThat(Day15.part1("2,1,3"), `is`(10L))
        assertThat(Day15.part1("1,2,3"), `is`(27L))
        assertThat(Day15.part1("2,3,1"), `is`(78L))
        assertThat(Day15.part1("3,2,1"), `is`(438L))
        assertThat(Day15.part1("3,1,2"), `is`(1836L))
    }

    @Test
    fun part1() {
        assertThat(Day15.part1(loadStringListWithoutBlanks("/day15_data.txt")[0]), `is`(1009L))
    }

    @Test
    @Ignore("too slow to run routinely")
    fun part2_simple() {
        assertThat(Day15.part2("0,3,6"), `is`(175594L))
        assertThat(Day15.part2("1,3,2"), `is`(2578L))
        assertThat(Day15.part2("2,1,3"), `is`(3544142L))
        assertThat(Day15.part2("1,2,3"), `is`(261214L))
        assertThat(Day15.part2("2,3,1"), `is`(6895259L))
        assertThat(Day15.part2("3,2,1"), `is`(18L))
        assertThat(Day15.part2("3,1,2"), `is`(362L))
    }

    @Test
    @Ignore("too slow to run routinely")
    fun part2() {
        assertThat(Day15.part2(loadStringListWithoutBlanks("/day15_data.txt")[0]), `is`(62714L))
    }

}


