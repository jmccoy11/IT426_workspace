/*
 * Jonnathon McCoy
 * 01/20/2018
 * Calculator.java
 *
 * This class will contain the MVC for the Calculator App. It will also be responsible for
 * the arithmetic operations for the CalculatorApp.
 *
 * Many of the Clean Code principles were used in the creation of this project
 * to help reinforce the topics discussed in Clean Code Chapters 1-4. It is
 * quite possible I may be misunderstanding and misusing some of these principles
 * but I am trying.
 */

package calculator;

import enums.FunctionType;
import interfaces.EvaluateExpression;

/**
 * This class will contain the MVC for the Calculator App.
 *
 * TODO: Redo the class JavaDoc
 *
 * @author Jonnathon McCoy
 * @version 0.1
 */
public class Calculator {
    
    private static final int BASE_TEN = 10;
    
    private String displayOutput;
    private Integer firstOperator;
    private Integer secondOperator;
    private EvaluateExpression operand;
    private Integer result;
    
    public Calculator() {
        displayOutput = "0";
        firstOperator = 0;
        secondOperator = 0;
        operand = null;
        result = 0;
    }
    
    public String getDisplayOutput() {
        return displayOutput;
    }
    
    public void evaluateOperator(String input) {
        updateDisplayOutput(Integer.valueOf(input));
        result = Integer.valueOf(displayOutput);
    }
    
    private void updateDisplayOutput(Integer value) {
        setFirstOrSecondOperator(value);
        setDisplayOutput(value.toString());
    }
    
    private void setFirstOrSecondOperator(Integer operator) {
        if (operationHasBeenPerformed()) {
            secondOperator = Integer.valueOf(displayOutput) * BASE_TEN + operator;
        } else {
            firstOperator = Integer.valueOf(displayOutput) * BASE_TEN + operator;
        }
    }
    
    private boolean operationHasBeenPerformed() {
        return operand != null;
    }
    
    private void setDisplayOutput(String input) {
        if (displayOutput.equals("0")) {
            displayOutput = input;
        } else {
            displayOutput += input;
        }
    }
    
    public void resetDisplayOutput() {
        displayOutput = "0";
        secondOperator = 0;
    }
    
    public void evaluateTool(FunctionType functionType) {
        if (functionType.equals(FunctionType.CLEAR)) {
            clearLastInput();
        } else if (functionType.equals(FunctionType.CLEAR_EVERYTHING)) {
            clearEverything();
        } else if (functionType.equals(FunctionType.ENTER)) {
            evaluateOperand(operand);
        }
    }
    
    public void evaluateOperand(EvaluateExpression function) {
        displayOutput = "0";
        operand = function;
        if (expressionComplete()) {
            evaluateExpression();
        }
    }
    
    private boolean expressionComplete() {
        return secondOperator != 0;
    }
    
    private void evaluateExpression() {
        result = operand.evaluateExpression(firstOperator, secondOperator);
        firstOperator = result;
        displayOutput = result.toString();
    }
    
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
    
    private void clearLastInput() {
        if (expressionComplete()) {
            secondOperator = 0;
        } else {
            firstOperator = 0;
        }
        resetDisplayOutput();
    }
    
    private void clearEverything() {
        displayOutput = "0";
        firstOperator = 0;
        secondOperator = 0;
        operand = null;
        result = 0;
    }
}
