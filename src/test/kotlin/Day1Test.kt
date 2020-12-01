import Util.loadIntList
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class Day1Test {

    @Test
    fun simpleTest() {
        assertThat(Day1.answer1(2020, listOf(1721, 232, 299, 1232, 434, 4341, 1)), `is`(514579))
    }

    @Test
    fun day1Test() {
        assertThat(Day1.answer1(2020, loadIntList("/day1_data.txt")), `is`(1018944))
    }

    @Test
    fun day1Test_v2() {
        assertThat(Day1.answer2(2020, loadIntList("/day1_data.txt")), `is`(1018944))
    }

}


