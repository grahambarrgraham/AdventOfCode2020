import java.util.*

/**
 * https://adventofcode.com/2020/day/18
 */
object Day18 {

    fun part1(strings: List<String>): Long = strings.map { eval(it) { _, _ -> true } }.sum()

    fun part2(strings: List<String>): Long = strings.map { eval(it) { op1, op2 -> !((op1 == "+") && (op2 == "*")) } }.sum()

    private fun eval(expression: String, precedence: (String, String) -> Boolean): Long =
            evaluate(expression.split(' ')
                    .flatMap { splitBracket(it) }
                    .toMutableList(), precedence)

    private fun splitBracket(it: String): List<String> =
            it.filter { it == '(' }.flatMap { listOf("(") } +
                    listOf(it.filterNot { it == '(' || it == ')' }) +
                    it.filter { it == ')' }.flatMap { listOf(")") }

    private fun evaluate(tokens: MutableList<String>, precedence: (String, String) -> Boolean): Long {
        val values = Stack<Long>()
        val ops = Stack<String>()

        while (tokens.isNotEmpty()) {

            var token = tokens.removeAt(0)

            when (token) {
                "(" -> ops.push(token)
                ")" -> {
                    while (ops.peek() != "(") values.push(applyOp(ops.pop(),
                            values.pop(),
                            values.pop()))
                    ops.pop()
                }
                "+", "*" -> {
                    while (!ops.empty() && hasPrecedence(token, ops.peek(), precedence)) values.push(applyOp(ops.pop(),
                            values.pop(),
                            values.pop()))
                    ops.push(token)
                }
                else -> {
                    values.push(token.toLong())
                }
            }
        }

        while (!ops.empty()) values.push(applyOp(ops.pop(),
                values.pop(),
                values.pop()))
        return values.pop()
    }

    private fun hasPrecedence(op1: String, op2: String, precedence: (String, String) -> Boolean): Boolean {
        if (op2 in listOf("(", ")")) return false
        return precedence(op1, op2)
    }

    private fun applyOp(op: String, b: Long, a: Long): Long = when (op) {
        "+" -> a + b
        "*" -> a * b
        else -> 0L
    }

}
