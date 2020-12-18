import java.util.*
import kotlin.system.exitProcess

object EvaluateString {
    fun evaluate(expression: String): Long {
        val tokens = expression.toCharArray()
        val values = Stack<Long>()
        val ops = Stack<Char>()
        var i = 0
        while (tokens.isNotEmpty()) {
            if (tokens[i] == ' ') {
                i++
                continue
            }
            if (tokens[i] >= '0' && tokens[i] <= '9') {
                val sbuf = StringBuffer()
                while (i < tokens.size && tokens[i] >= '0' && tokens[i] <= '9') sbuf.append(tokens[i++])
                values.push(sbuf.toString().toLong())
                i--
            } else if (tokens[i] == '(') ops.push(tokens[i]) else if (tokens[i] == ')') {
                while (ops.peek() != '(') values.push(applyOp(ops.pop(),
                        values.pop(),
                        values.pop()))
                ops.pop()
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') { // While top of 'ops' has same
                while (!ops.empty() &&
                        hasPrecedence(tokens[i],
                                ops.peek())) values.push(applyOp(ops.pop(),
                        values.pop(),
                        values.pop()))
                // Push current token to 'ops'.
                ops.push(tokens[i])
            }
            i++
        }
        while (!ops.empty()) values.push(applyOp(ops.pop(),
                values.pop(),
                values.pop()))
        return values.pop()
    }

    fun hasPrecedence(
            op1: Char, op2: Char): Boolean {
        if (op2 == '(' || op2 == ')') return false
        return if ((op1 == '+' || op1 == '-') &&
                (op2 == '*' || op2 == '/')) false else true
    }

    fun applyOp(op: Char, b: Long, a: Long): Long = when (op) {
        '+' -> a + b
        '*' -> a * b
        else -> 0L
    }

}
