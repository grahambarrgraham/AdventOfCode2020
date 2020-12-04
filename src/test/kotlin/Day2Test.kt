import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day2Test {

    @Test
    fun part1_simple() {
        assertThat(Day2.part1(listOf("100-100 a: a", "1-100 [: [", "1-1 a: [", "0-1 a: bcde", "1-3 a: abcde", "1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc")), `is`(5))
    }

    @Test
    fun part1() {
        assertThat(Day2.part1(loadStringListWithoutBlanks("/day2_data.txt")), `is`(519))
    }

    @Test
    fun part2_simple() {
        assertThat(Day2.part2(listOf("0-1 c: c")), `is`(1))
        assertThat(Day2.part2(listOf("0-0 c: c")), `is`(0))
        assertThat(Day2.part2(listOf("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc")), `is`(1))
    }

    @Test
    fun part2() {
        assertThat(Day2.part2(loadStringListWithoutBlanks("/day2_data.txt")), `is`(708))
    }


}


