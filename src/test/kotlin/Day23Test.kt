import Day23.part1
import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day23Test {

    @Test
    fun part1_simple() {
        assertThat(part1("389125467", 10), `is`("92658374"))
        assertThat(part1("389125467", 100), `is`("67384529"))
    }

    @Test
    fun part1() {
        assertThat(part1("583976241", 100), `is`("24987653"))
    }

    @Test
    fun part2() {
        assertThat(Day23.part2("583976241"), `is`(442938711161L))
    }

}


