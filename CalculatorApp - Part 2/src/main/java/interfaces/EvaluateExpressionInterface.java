/*
 * Jonnathon McCoy
 * 01/26/2018
 * EvaluateExpressionInterface.java
 *
 * This Interface dictates that any Function that is created must have the capability
 * to call evaluateExpression.
 *
 * To allow this to be called, whether it's add, subtract, multiply, divide, or none, the
 * buttons store a function grouped together by the EvaluateExpressionInterface
 * so that evaluate Expression can be called at anytime the function is retrieved
 * from the button.
 */

package interfaces;

/**
 * This Interface dictates that any Function that is created must have the capability
 * to call evaluateExpression.
 *
 * To allow this to be called, whether it's add, subtract, multiply, divide, or none, the
 * buttons store a function grouped together by the EvaluateExpressionInterface
 * so that evaluate Expression can be called at anytime the function is retrieved
 * from the button.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public interface EvaluateExpressionInterface {
    
    /**
     * apparently the public modifier was redundant and unneccessary according to IntelliJ
     *
     * Evaluates the expression of the function.
     * @param firstValue
     * @param secondValue
     * @return The result of the evaluated expression.
     */
    int evaluateExpression(int firstValue, int secondValue);
}
