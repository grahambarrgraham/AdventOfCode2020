import java.util.*

/**
 * https://adventofcode.com/2020/day/8
 */
object Day8 {

    data class Instruction(val type: String, val value: Int, val id: UUID = UUID.randomUUID())

    fun part1(strings: List<String>): Int =
            loop(parse(strings)).acc

    fun part2(strings: List<String>): Int =
            mutations(parse(strings))
                    .map { loop(it) }
                    .first { it.idx == it.size }.acc

    private fun mutations(instructions: List<Instruction>): Sequence<List<Instruction>> =
            sequence {
                yield(instructions)
                instructions.forEachIndexed { index,
                                              instruction ->
                    if (instruction.type == "nop" && instruction.value != 0) {
                        yield(mutate(index, Instruction("jmp", instruction.value), instructions))
                    }
                    if (instruction.type == "jmp" && instruction.value != 0) {
                        yield(mutate(index, Instruction("nop", instruction.value), instructions))
                    }
                }
            }

    private fun mutate(idx: Int, instruction: Instruction, instructions: List<Instruction>): List<Instruction> {
        val mutableListOf = instructions.toMutableList()
        mutableListOf[idx] = instruction
        return mutableListOf
    }

    data class Result(val idx: Int, val acc: Int, val size: Int)

    private fun loop(instructions: List<Instruction>): Result {
        var acc = 0
        var idx = 0
        val visited: MutableSet<Instruction> = mutableSetOf()
        var instruction: Instruction = instructions[idx]
        while (true) {
            visited.add(instruction)
            when (instruction.type) {
                "acc" -> {
                    acc += instruction.value
                    idx++
                }
                "jmp" -> {
                    idx += instruction.value
                }
                else -> {
                    idx++
                }
            }
            if (idx >= instructions.size) break
            instruction = instructions[idx]
            if (visited.contains(instruction)) break
        }

        return Result(idx, acc, instructions.size)
    }

    private fun parse(strings: List<String>): List<Instruction> {
        return strings.map { it.parse() }
    }

    private fun String.parse(): Instruction {
        val split = this.split("\\s+".toRegex())
        return Instruction(split[0], split[1].toInt())
    }

}
