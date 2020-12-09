import Util.loadLongList
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day9Test {

    @Test
    fun part1_simple() {
        assertThat(Day9.part1(loadLongList("/day9_simple.txt"), 5), `is`(127L))
    }

    @Test
    fun part1() {
        assertThat(Day9.part1(loadLongList("/day9_data.txt"), 25), `is`(31161678L))
    }

    @Test
    fun part2_simple() {
        assertThat(Day9.part2(loadLongList("/day9_simple.txt"), 127L), `is`(62L))
    }

    @Test
    fun part2() {
        assertThat(Day9.part2(loadLongList("/day9_data.txt"), 31161678L), `is`(5453868L))
    }

}


