/**
 * https://adventofcode.com/2020/day/23
 */
object Day23 {

    fun part1(input: String, rounds: Int): String {

        val totalCups = 9
        var state = loadCups(input, totalCups)

        repeat(rounds) {
            round(state, totalCups)
        }

        return readNextNFrom(state.map[1]!!, 8)
                .map { it.value.toString() }
                .fold("") { acc, s -> acc + s }
    }

    fun part2(input: String): Long {
        val totalCups = 1000000
        var state = loadCups(input, totalCups)

        repeat(10000000) {
            round(state, totalCups)
        }

        val node1 = state.map[1]!!.next
        val node2 = node1.next

        return node1.value.toLong() * node2.value.toLong()
    }

    private fun loadCups(input: String, totalCups: Int): GameState {
        val cups = (input.map { it.toString().toInt() }.toList() + (10..totalCups)).map{Node(it)}
        cups.forEachIndexed{index, it -> if(index < cups.size - 1) it.next = cups[index+1] else it.next = cups[0] }
        return GameState(cups[0], cups.toSet(), cups.associateBy { it.value })
    }

    data class Node(val value: Int) {
        var next: Node = this
    }

    data class GameState(val start: Node, val cups: Set<Node>, val map: Map<Int, Node>) {
        var current: Node = start
    }

    private fun round(state: GameState, size: Int) {
        val pick= pickNextNFrom(state.current, 3)
        val destination = pickDestination(state, pick, size)
        insertPick(destination, pick)
        state.current = state.current.next
    }

    private fun insertPick(destination: Node, pick: List<Node>) {
        val next = destination.next
        destination.next = pick.first()
        pick.last().next = next
    }

    private fun pickDestination(state: GameState, pick: List<Node>, size: Int): Node {
        var destination = state.current.value
        val pickValues = pick.map { it.value }
        while (true) {
            destination = if (destination == 1) size else (destination - 1)
            if (!pickValues.contains(destination)) {
                return state.map[destination]!!
            }
        }
    }

    private fun pickNextNFrom(current: Node, n: Int): List<Node> {
        val pick = readNextNFrom(current, n)
        current.next = pick.last().next
        return pick
    }

    private fun readNextNFrom(current: Node, n: Int): List<Node> {
        val pick = mutableListOf<Node>()
        var next = current

        repeat(n) {
            next = next.next
            pick.add(next)
        }
        return pick
    }

}
