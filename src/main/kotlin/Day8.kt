import java.util.*

/**
 * https://adventofcode.com/2020/day/8
 */
object Day8 {

    fun part1(strings: List<String>): Int =
            execute(parse(strings)).acc

    fun part2(strings: List<String>): Int =
            mutations(parse(strings))
                    .map { execute(it) }
                    .first { it.idx == it.size }.acc

    private data class Instruction(val type: String, val value: Int, val id: UUID = UUID.randomUUID())

    private data class Result(val idx: Int, val acc: Int, val size: Int)

    private fun mutations(instructions: List<Instruction>): Sequence<List<Instruction>> =
            sequence {
                instructions
                        .forEachIndexed { index, instruction ->
                            when {
                                instruction.isOp("nop") ->
                                    yield(mutate(index, Instruction("jmp", instruction.value), instructions))

                                instruction.isOp("jmp") ->
                                    yield(mutate(index, Instruction("nop", instruction.value), instructions))
                            }
                        }
            }

    private fun Instruction.isOp(s: String) = type == s && value != 0

    private fun mutate(idx: Int, instruction: Instruction, instructions: List<Instruction>): List<Instruction> {
        val mutableListOf = instructions.toMutableList()
        mutableListOf[idx] = instruction
        return mutableListOf
    }

    private fun execute(instructions: List<Instruction>): Result {
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

    private fun parse(strings: List<String>): List<Instruction> = strings.map { it.parse() }

    private fun String.parse(): Instruction {
        val split = this.split("\\s+".toRegex())
        return Instruction(split[0], split[1].toInt())
    }

}
