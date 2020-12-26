/**
 * https://adventofcode.com/2020/day/22
 */
object Day22 {

    fun part1(strings: List<List<String>>): Long {
        val player1 = strings[0].drop(1).map { it.toLong() }.toMutableList()
        val player2 = strings[1].drop(1).map { it.toLong() }.toMutableList()

        while (player1.isNotEmpty() && player2.isNotEmpty()) {
            val card1 = player1.removeAt(0)
            val card2 = player2.removeAt(0)
            if (card1 > card2) player1.addAll(listOf(card1, card2))
            else player2.addAll(listOf(card2, card1))
        }

        val winner = if (player1.isEmpty()) player2 else player1

        return winner.reversed().foldIndexed(0L) { index, acc, i -> acc + ((index + 1) * i) }

    }

    data class GameState(val player1: List<Long>, val player2: List<Long>)


    fun part2(strings: List<List<String>>): Long {

        val player1 = strings[0].drop(1).map { it.toLong() }
        val player2 = strings[1].drop(1).map { it.toLong() }

        val (newState, isPlayer1Winner) = game(GameState(player1, player2))

        val winner = if (isPlayer1Winner) newState.player1 else newState.player2

        return winner.reversed().foldIndexed(0L) { index, acc, i -> acc + ((index + 1) * i) }

    }

    private fun game(gameState: GameState): Pair<GameState, Boolean> {

        val history = mutableSetOf<GameState>()

        val player1 = gameState.player1.toMutableList()
        val player2 = gameState.player2.toMutableList()

        while (player1.isNotEmpty() && player2.isNotEmpty()) {

            val audit = GameState(player1.toList(), player2.toList())

            if (history.contains(audit)) {
                return Pair(audit, true)
            } else {
                history.add(audit)
            }

            val card1 = player1.removeAt(0)
            val card2 = player2.removeAt(0)

            val player1IsWinner =
                    if (player1.size >= card1 && player2.size >= card2) {
                        game(GameState(player1.take(card1.toInt()), player2.take(card2.toInt()))).second
                    }
                    else
                        card1 > card2

            if (player1IsWinner)
                player1.addAll(listOf(card1, card2))
            else
                player2.addAll(listOf(card2, card1))
        }

        val newState = GameState(player1, player2)

        return Pair(newState, player2.isEmpty())
    }

}
