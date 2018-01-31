/*
 * Jonnathon McCoy
 * 01/20/2018
 * Calculator.java
 *
 * This class will contain the Model for the class that will determine what variables
 * input will end up in as well as when to evaluate expressions based on FunctionType.
 *
 * Many of the Clean Code principles were used in the creation of this project
 * to help reinforce the topics discussed in Clean Code Chapters 1-4. It is
 * quite possible I may be misunderstanding and misusing some of these principles
 * but I am trying.
 */

package calculator;

import enums.FunctionType;
import interfaces.EvaluateExpressionInterface;

/**
 * This class will contain the model for the class that will determine what variables
 * input will end up in as well as when to evaluate expressions based on FunctionType.
 *
 * @author Jonnathon McCoy
 * @version 0.1
 */
public class Calculator {
    
    private static final int BASE_TEN = 10;
    
    private String displayOutput;
    private Integer firstOperator;
    private Integer secondOperator;
    private EvaluateExpressionInterface operand;
    private Integer result;
    
    /**
     * Constructor for Calculator.
     *
     * Instantiates default values.
     */
    public Calculator() {
        displayOutput = "0";
        firstOperator = 0;
        secondOperator = 0;
        operand = null;
        result = 0;
    }
    
    /**
     * Getter for the output that should be displayed on the CalculatorUI
     * @return String representation of the number stored in the Calculator
     */
    public String getDisplayOutput() {
        return displayOutput;
    }
    
    /**
     * When the Calculator receives input, evaluate the operator and update result
     * to be the current display. That way, if Enter is pressed without an operand
     * set, the display will update to what the result was and visa versa. This is
     * important for evaluating chaining operations.
     * @param input - String value of the number that was pressed.
     */
    public void evaluateOperator(String input) {
        updateDisplayOutput(Integer.valueOf(input));
        result = Integer.valueOf(displayOutput);
    }
    
    private void updateDisplayOutput(Integer value) {
        setFirstOrSecondOperator(value);
        setDisplayOutput(value.toString());
    }
    
    /*
     * Check whether the input integer needs to go into the first or second operator.
     * This is important for evaluating chaining operations.
     */
    private void setFirstOrSecondOperator(Integer operator) {
        if (hasOperationBeenPerformed()) {
            secondOperator = Integer.valueOf(displayOutput) * BASE_TEN + operator;
        } else {
            firstOperator = Integer.valueOf(displayOutput) * BASE_TEN + operator;
        }
    }
    
    private boolean hasOperationBeenPerformed() {
        return operand != null;
    }
    
    private void setDisplayOutput(String input) {
        if (displayOutput.equals("0")) {
            displayOutput = input;
        } else {
            displayOutput += input;
        }
    }
    
    /**
     * Reset the display output to "0".
     */
    public void resetDisplayOutput() {
        displayOutput = "0";
    }
    
    /**
     * Evaluate which tool button has been pressed and call the appropriate
     * function.
     * @param functionType - FunctionType from enum
     */
    public void evaluateTool(FunctionType functionType) {
        if (functionType.equals(FunctionType.CLEAR)) {
            clearLastInput();
        } else if (functionType.equals(FunctionType.CLEAR_EVERYTHING)) {
            clearEverything();
        } else if (functionType.equals(FunctionType.ENTER)) {
            evaluateOperand(operand);
        }
    }
    
    /**
     * Evaluate the the Operand that was pressed.
     * @param function - Function grouped by EvaluateExpressionInterface so that evaluateExpression
     *                 can be called on the function call.
     */
    public void evaluateOperand(EvaluateExpressionInterface function) {
        displayOutput = "0";
        operand = function;
        if (expressionComplete()) {
            evaluateExpression();
        }
    }
    
    //If the second operator is not equal to zero, that means we are not currently in a chain
    //of operations.
    private boolean expressionComplete() {
        return secondOperator != 0;
    }
    
    
    //Call evaluateExpression from EvaluateExpressionInterface and pass in the operators. The
    //Arithmetic Function class that is currently in operand will have the proper sequence
    //from which to perform the arithmetic.
    private void evaluateExpression() {
        result = operand.evaluateExpression(firstOperator, secondOperator);
        firstOperator = result;
        setDisplayOutput(result.toString());
    }
    
    //Clear only the last input entered and reset the display.
    private void clearLastInput() {
        if (expressionComplete()) {
            secondOperator = 0;
        } else {
            firstOperator = 0;
        }
        resetDisplayOutput();
    }
    
    //Reset everything to default values.
    private void clearEverything() {
        displayOutput = "0";
        firstOperator = 0;
        secondOperator = 0;
        operand = null;
        result = 0;
    }
    
    /**
     * String representation of the Class.
     * @return String representation of the Class.
     */
    @Override
    public String toString() {
        return "Calculator{" +
                "displayOutput='" + displayOutput + '\'' +
                ", firstOperator=" + firstOperator +
                ", secondOperator=" + secondOperator +
                ", operand=" + operand +
                ", result=" + result +
                '}';
    }
}
