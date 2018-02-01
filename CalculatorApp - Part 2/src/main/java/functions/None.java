/*
 * Jonnathon McCoy
 * 01/26/2018
 * Multiply.java
 *
 * All buttons need a Function to fulfill the constructor. If there is not function
 * to evaluate, it's replaced with this "None" function.
 */

package functions;

import interfaces.Operation;

/**
 * All buttons need a Function to fulfill the constructor. If there is not function
 * to evaluate, it's replaced with this "None" function.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class None implements Operation {
    
    /**
     * All buttons need a Function to fulfill the constructor. If there is not function
     * to evaluate, it's replaced with this "None" function and will return 0.
     * @param firstValue
     * @param secondValue
     * @return Returns 0.
     */
    @Override
    public int evaluateExpression(int firstValue, int secondValue) {
        return 0;
    }
}
