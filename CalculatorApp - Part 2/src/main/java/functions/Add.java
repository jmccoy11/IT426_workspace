/*
 * Jonnathon McCoy
 * 01/26/2018
 * Add.java
 *
 * This class is the function to add two numbers.
 *
 * To allow this to be called, whether it's add, subtract, multiply, divide, the
 * buttons store a function grouped together by the EvaluateExpressionInterface
 * so that evaluate Expression can be called at anytime the function is retrieved
 * from the button.
 */

package functions;

import interfaces.Operation;

/**
 * This class is the function to add two numbers together.
 *
 * To allow this to be called, whether it's add, subtract, multiply, divide, the
 * buttons store a function grouped together by the Operation
 * so that evaluate Operation can be called at anytime the function is retrieved
 * from the button.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Add implements Operation {
    
    /**
     * Evaluate the expression to add two numbers.
     * @param firstValue
     * @param secondValue
     * @return result of firstValue + secondValue
     */
    @Override
    public int evaluateExpression(int firstValue, int secondValue) {
        return firstValue + secondValue;
    }
}
