/*
 * Jonnathon McCoy
 * 01/26/2018
 * Divide.java
 *
 * This class is the function to divide two numbers. This function also checks
 * that the second number is not zero as we cannot divide by zero.
 *
 * To allow this to be called, whether it's add, subtract, multiply, divide, the
 * buttons store a function grouped together by the EvaluateExpressionInterface
 * so that evaluate Expression can be called at anytime the function is retrieved
 * from the button.
 */

package functions;

import interfaces.EvaluateExpressionInterface;

/**
 * This class is the function to divide two numbers. This function also checks
 * that the second number is not zero as we cannot divide by zero.
 *
 * To allow this to be called, whether it's add, subtract, multiply, divide, the
 * buttons store a function grouped together by the EvaluateExpressionInterface
 * so that evaluate Expression can be called at anytime the function is retrieved
 * from the button.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Divide implements EvaluateExpressionInterface {
    
    /**
     * Check that the secondValue is not 0 and then evaluate the expression to divide two numbers.
     * @param firstValue
     * @param secondValue
     * @return result of firstValue / secondValue
     */
    @Override
    public int evaluateExpression(int firstValue, int secondValue) {
        if (secondValue == 0) {
            return 0;
        }
        
        return firstValue/secondValue;
    }
}
