import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day18Test {

    @Test
    fun part1_simple() {
        println(26 + 437 + 12240 + 13632)
        assertThat(Day18.part1(loadStringListWithoutBlanks("/day18_simple.txt")), `is`(26 + 437 + 12240 + 13632L))
    }

    @Test
    fun part1() {
        assertThat(Day18.part1(loadStringListWithoutBlanks("/day18_data.txt")), `is`(4491283311856L))
    }

    @Test
    fun part2() {
        assertThat(Day18.part2(loadStringListWithoutBlanks("/day18_data.txt")), `is`(68852578641904L))
    }

}


