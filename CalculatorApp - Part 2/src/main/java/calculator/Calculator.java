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
import interfaces.Operation;

/**
 * This class will contain the model for the class that will determine what variables
 * input will end up in as well as when to evaluate expressions based on FunctionType.
 *
 * @author Jonnathon McCoy
 * @version 0.1
 */
public class Calculator {
    
    private static final int BASE_TEN = 10;
    
    private Integer calculatorInput;
    private String displayOutput;
    private Integer firstOperator;
    private Integer secondOperator;
    private Operation operand;
    private Integer result;
    
    /**
     * Constructor for Calculator.
     *
     * Instantiates default values.
     */
    public Calculator() {
        calculatorInput = 0;
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

//    /**
//     * Get result as string.
//     * @return String representation of the result.
//     */
//    public String getResultAsString() {
//        return result.toString();
//    }
    
    /**
     * Evaulate if the input needs to go into the first or second operator and
     * update the display with the operator that needs to be set.
     * @param input - String value of the number that was pressed.
     */
    public void evaluateOperator(String input) {
        calculatorInput = Integer.valueOf(input);
        if (expressionComplete()) {
            secondOperator = 0;
        }
        resetResult();
        determineOperatorToSet();
    }
    
    private void resetResult() {
        result = 0;
    }
    
    private void determineOperatorToSet() {
        if (operand == null) {
            updateDisplayToFirstOperator();
        } else {
            updateDisplayToSecondOperator();
        }
    }
    
    private void updateDisplayToFirstOperator() {
        setFirstOperator();
        setDisplayOutput(firstOperator.toString());
    }
    
    private void setFirstOperator() {
        if (firstOperator != 0) {
            firstOperator = firstOperator * BASE_TEN + calculatorInput;
        } else {
            firstOperator = calculatorInput;
        }
    }
    
    private void updateDisplayToSecondOperator() {
        setSecondOperator();
        setDisplayOutput(secondOperator.toString());
    }
    
    private void setSecondOperator() {
        if (secondOperator != 0) {
            secondOperator = secondOperator * BASE_TEN + calculatorInput;
        } else {
            secondOperator = calculatorInput;
        }
    }
    
    private void setDisplayOutput(String input) {
        displayOutput = input;
    }
    
    /**
     * Reset the display Output to "0"
     */
    public void resetDisplayOutput() {
        calculatorInput = 0;
        displayOutput = "0";
        secondOperator = 0;
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
     * @param function - Function grouped by Operation so that evaluateExpression
     *                 can be called on the function call.
     */
    public void evaluateOperand(Operation function) {
        displayOutput = "0";
        if (operand == null) {
            operand = function;
        } else if (operand.equals(function)) {
            resetResult();
            evaluateExpression();
        } else {
            if (result != 0) {
                secondOperator = 0;
                resetResult();
            }
            evaluateExpression();
            operand = function;
        }
    }
    
    //If the second operator is not equal to zero, that means we are not currently in a chain
    //of operations.
    private boolean expressionComplete() {
        return result != 0;
    }
    
    
    //Call evaluateExpression from Operation and pass in the operators. The
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
        calculatorInput = 0;
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
