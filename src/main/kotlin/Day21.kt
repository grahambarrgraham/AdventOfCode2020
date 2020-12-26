/**
 * https://adventofcode.com/2020/day/21
 */
object Day21 {

    fun part1(strings: List<String>): Long = 0

    fun part2(strings: List<String>): Long = 0


    @JvmStatic
    fun main(args: Array<String>) {
        val input = Util.loadStringListWithoutBlanks("/day21_simple.txt").map { read(it) }
        val hypothesis = getStartingHypothetheses(input)
        println("Input $input")
        println("Hypothetis $hypothesis")
        //for each hypothesis, iterate through rules and filter out the impossible

        val v = hypothesis.mapValues {
            it.value.filter { allergen -> consistentWith(it.key, allergen, input) }
        }

        println("After : $v")

        val v2 = v.mapValues {
            it.value.filter { allergen -> consistentWith(it.key, allergen, input) }
        }

        println("After : $v2")

        //dairy -> one of (trh fvjkl sbzzf mxmxvkd)
        //diary or fish -> one of (

    }

    private fun consistentWith(ingredient: String, allergen: String, input: List<Input>): Boolean {
        return input.fold(true) {acc, input -> acc && consistentWith1(ingredient, allergen, input)}
    }

    private fun consistentWith1(ingredient: String, allergen: String, input: Input): Boolean {
        if (input.alg.size == 1 && allergen == input.alg[0] && !input.ing.contains(ingredient))
            return false
        return true
    }

    private fun getStartingHypothetheses(input: List<Input>): MutableMap<String, Set<String>> {
        val hypothesisList = mutableMapOf<String, Set<String>>()

        for (input in input) {
            input.ing.forEach {
                hypothesisList.putIfAbsent(it, emptySet())
                hypothesisList[it] = hypothesisList[it]!! + input.alg
            }
        }
        return hypothesisList
    }

    private fun read(it: String): Input {
        var (aS, bS) = "(.+) \\(contains (.+)\\)".toRegex().find(it)!!.destructured
        return Input(aS.trim().split(' '), bS.trim().split(", "))
    }

    data class Input(val ing: List<String>, val alg: List<String>)


    //mxmxvkd (diary, fish, any)
    //kfcds (diary, fish, any)
}
