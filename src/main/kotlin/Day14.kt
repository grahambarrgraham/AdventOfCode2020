import kotlin.math.pow

/**
 * https://adventofcode.com/2020/day/14
 */
object Day14 {

    private val maskRegex = "mask = (.+)".toRegex()
    private val registerRegexp = "mem\\[(\\d+)] = (\\d+)".toRegex()

    fun part1(strings: List<String>): Long {

        val memory = mutableMapOf<Int, Long>()

        loadInstructions(strings)
                .forEach { instruction ->
                    instruction.data.forEach { data ->
                        memory[data.key] = applyMaskPart(
                                instruction.mask,
                                binaryString(data.value.toInt(), 36),
                                this::applyMaskPart1)
                                .toLong(2)
                    }
                }

        return memory.values.sum()
    }

    fun part2(strings: List<String>): Long {

        val memory = mutableMapOf<String, Long>()

        data class Store(val addresses: Sequence<String>, val value: Long)

        loadInstructions(strings)
                .flatMap { instruction ->
                    instruction.data.map {
                        Store(expandMask(applyMaskPart(
                                instruction.mask,
                                binaryString(it.key, 36),
                                this::applyMaskPart2)),
                                it.value)
                    }
                }.forEach { pair ->
                    pair.addresses.forEach { memory[it] = pair.value }
                }

        return memory.values.sum()
    }

    data class Instruction(val data: Map<Int, Long>, val mask: String)

    fun applyMaskPart(mask: String, input: String, convert: (Char, Char) -> Char): String =
            String(input.toCharArray().mapIndexed { i, _ -> convert(mask[i], input[i]) }.toCharArray())

    private fun applyMaskPart1(maskChar: Char, valChar: Char): Char =
            if (maskChar == '0') '0' else if (maskChar == '1') '1' else valChar

    fun applyMaskPart2(maskChar: Char, valChar: Char): Char =
            if (maskChar == 'X') 'X' else if (maskChar == '1') '1' else valChar

    fun expandMask(s: String): Sequence<String> = combinations(s).map { instantiateMask(s, it) }

    private fun instantiateMask(s: String, entry: Map<Int, Char>): String {
        val result = s.toCharArray()
        entry.forEach {
            result[it.key] = it.value
        }
        return String(result)
    }

    private fun combinations(s: String): Sequence<Map<Int, Char>> = sequence {
        val maskIndexes = s.mapIndexed { index, it -> if (it == 'X') index else null }.filterNotNull()
        val numberOfCombos = 2.toFloat().pow(maskIndexes.size).toInt() - 1

        for (it in 0..numberOfCombos) {
            val comboS = binaryString(it, maskIndexes.size)
            val result = mutableMapOf<Int, Char>()
            maskIndexes.forEachIndexed { i, maskIndex -> result[maskIndex] = comboS[i] }
            yield(result)
        }
    }
    private fun binaryString(it1: Int, length: Int) = Integer.toBinaryString(it1).padStart(length, '0')

    private fun loadInstructions(strings: List<String>) =
            splitByInstructionGroup(strings).map { it -> parse(it) }.toList()

    private fun splitByInstructionGroup(strings: List<String>): Sequence<List<String>> = sequence {
        var i = 0;
        while (i < strings.size) {
            val list = listOf(strings[i]) + strings.subList(i + 1, strings.size).takeWhile { it.startsWith("mem") }
            yield(list)
            i += list.size
        }
    }

    private fun parse(strings: List<String>): Instruction =
            Instruction(
                    loadMemory(strings),
                    maskRegex.find(strings[0])!!.destructured.component1())

    private fun loadMemory(strings: List<String>) =
            strings
                    .drop(1)
                    .map { registerRegexp.find(it)!!.destructured }
                    .associate { it.component1().toInt() to it.component2().toLong() }

}
