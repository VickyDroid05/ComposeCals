package com.waggieetales.composecals.util

class CalculatorUtil {

    fun evaluateCals(expr: String): Int {
        val regex = Regex("""(\d+)([+\-*/])(\d+)""")
        val match = regex.matchEntire(expr.replace(" ", ""))
            ?: throw IllegalArgumentException("Invalid expression: $expr")

        val (left, operator, right) = match.destructured
        val a = left.toInt()
        val b = right.toInt()

        return when (operator) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> if (b != 0) a / b else throw ArithmeticException("Division by zero")
            else -> throw IllegalArgumentException("Unknown operator: $operator")
        }
    }
}