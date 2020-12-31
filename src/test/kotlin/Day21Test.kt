import Util.loadStringListWithoutBlanks
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Day21Test {

    @Test
    fun part1_simple() {
        assertThat(Day21.part1(loadStringListWithoutBlanks("/day21_simple.txt")), `is`(5L))
    }

    @Test
    fun part1() {
        assertThat(Day21.part1(loadStringListWithoutBlanks("/day21_data.txt")), `is`(1930L))
    }

    @Test
    fun part2_simple() {
        assertThat(Day21.part2(loadStringListWithoutBlanks("/day21_simple.txt")), `is`("mxmxvkd,sqjhc,fvjkl"))
    }

    @Test
    fun part2() {
        assertThat(Day21.part2(loadStringListWithoutBlanks("/day21_data.txt")), `is`("spcqmzfg,rpf,dzqlq,pflk,bltrbvz,xbdh,spql,bltzkxx"))
    }

}


