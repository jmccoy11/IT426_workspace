/*
 * Jonnathon McCoy
 * 01/26/2018
 * Multiply.java
 *
 * This class is the function to subtract two numbers.
 *
 * To allow this to be called, whether it's add, subtract, multiply, divide, the
 * buttons store a function grouped together by the EvaluateExpressionInterface
 * so that evaluate Expression can be called at anytime the function is retrieved
 * from the button.
 */

package functions;

import interfaces.EvaluateExpressionInterface;

/**
 * This class is the function to subtract two numbers.
 *
 * To allow this to be called, whether it's add, subtract, multiply, divide, the
 * buttons store a function grouped together by the EvaluateExpressionInterface
 * so that evaluate Expression can be called at anytime the function is retrieved
 * from the button.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Subtract implements EvaluateExpressionInterface {
    
    /**
     * Evaluate the expression to subtract two numbers.
     * @param firstValue
     * @param secondValue
     * @return result of firstValue - secondValue
     */
    @Override
    public int evaluateExpression(int firstValue, int secondValue) {
        return firstValue - secondValue;
    }
}
