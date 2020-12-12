import kotlin.math.absoluteValue

/**
 * https://adventofcode.com/2020/day/12
 */
object Day12 {

    fun part1(strings: List<String>): Long {
        val ship = Ship()
        loadInstructions(strings).forEach { ship.move(it) }
        return ship.x.absoluteValue + ship.y.absoluteValue.toLong()
    }

    fun part2(strings: List<String>): Long {
        val ship = ShipAndWayPoint()
        loadInstructions(strings).forEach { ship.move(it) }
        return ship.x.absoluteValue + ship.y.absoluteValue.toLong()
    }

    private class ShipAndWayPoint(var x: Int = 0, var y: Int = 0, var wpX: Int = 10, var wpY: Int = 1) {
        fun move(instr: Instruction): Unit =
                when (instr.action) {
                    'N' -> wpY += instr.value
                    'S' -> wpY -= instr.value
                    'E' -> wpX += instr.value
                    'W' -> wpX -= instr.value
                    'F' -> {
                        x += wpX * instr.value; y += wpY * instr.value
                    }
                    else -> applyOrientation(instr)
                }

        fun applyOrientation(instr: Instruction): Unit {
            when (instr) {
                Instruction('R', 90) -> {
                    val oldY = wpY; wpY = wpX * -1; wpX = oldY
                }
                Instruction('L', 90) -> {
                    val oldY = wpY; wpY = wpX; wpX = -1 * oldY
                }
                Instruction('R', 270) -> applyOrientation(Instruction('L', 90))
                Instruction('L', 270) -> applyOrientation(Instruction('R', 90))
                else -> {
                    applyOrientation(Instruction('L', 90))
                    applyOrientation(Instruction('L', 90))
                }
            }
        }
    }

    private class Ship(var x: Int = 0, var y: Int = 0, var orientation: Char = 'E') {
        fun move(instr: Instruction): Unit =
                when (instr.action) {
                    'N' -> y += instr.value
                    'S' -> y -= instr.value
                    'E' -> x += instr.value
                    'W' -> x -= instr.value
                    'F' -> move(Instruction(orientation, instr.value))
                    else -> applyOrientation(instr)
                }

        val directions = listOf('N', 'E', 'S', 'W')

        fun applyOrientation(instr: Instruction) {
            val index = directions.indexOf(orientation)
            val targetIndex = (if (instr.action == 'L') index - instr.value / 90 else index + instr.value / 90) % 4
            orientation = directions[if (targetIndex < 0) 4 + targetIndex else targetIndex]
        }
    }

    private data class Instruction(val action: Char, val value: Int)

    private val instructionPattern = "([NSLREWF])(\\d+)".toRegex()

    private fun loadInstructions(strings: List<String>): List<Instruction> =
            strings.map {
                val (action, valueS) = instructionPattern.find(it)!!.destructured
                Instruction(action[0], valueS.toInt())
            }
}
