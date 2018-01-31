/*
 * Jonnathon McCoy
 * 01/26/2018
 * ButtonType.java
 *
 * This Enum defines what the Function Types in the Calculator. This enum is
 * used to determine if a button is a number (operator), arithmetic expression, or a tool.
 *
 * If a Button is and Operand, the arithmetic expression Add, Subtract, Multiply, Divide,
 * is determined upon button creation and applied to the FunctionType.
 */

package enums;

/**
 * This Enum defines what the Function Types in the Calculator. This enum is
 * used to determine if a button is a number (operator), arithmetic expression, or a tool.
 *
 * If a Button is and Operand, the arithmetic expression Add, Subtract, Multiply, Divide,
 * is determined upon button creation and applied to the FunctionType.
 */
public enum FunctionType {
    OPERATOR,
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    ENTER,
    CLEAR,
    CLEAR_EVERYTHING
}
