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
    
    private String displayInput;
    private Integer firstOperator;
    private Integer secondOperator;
    private EvaluateExpression operand;
    private Integer result;
    
    public Calculator() {
        displayInput = "0";
        firstOperator = 0;
        secondOperator = 0;
        operand = null;
        result = 0;
    }
    
    public String getDisplayInput() {
        return displayInput;
    }
    
    private void setDisplayInput(String input) {
        if (displayInput.equals("0")) {
            displayInput = input;
        } else {
            displayInput += input;
        }
    }
    
    private void updateDisplayInput(Integer operator) {
        if (operand != null) { //An operation has been performed and firstOperator is result
            secondOperator = Integer.valueOf(displayInput) * BASE_TEN + operator;
        } else {
            firstOperator = Integer.valueOf(displayInput) * BASE_TEN + operator;
        }
        
        setDisplayInput(operator.toString());
    
        result = Integer.valueOf(displayInput);
    }
    
    public void resetDisplayInput() {
        displayInput = "0";
        secondOperator = 0;
    }
    
    public void evaluateOperator(String value) {
        updateDisplayInput(Integer.valueOf(value));
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
        displayInput = "0";
        
        if (operand == null) {
            operand = function;
        } else {
            operand = function;
            if (secondOperator != 0) {
                result = operand.evaluateExpression(firstOperator, secondOperator);
                firstOperator = result;
                displayInput = result.toString();
            } else {
                operand = function;
            }
        }
    }
    
    @Override
    public String toString() {
        return "Calculator{" +
                "displayInput='" + displayInput + '\'' +
                ", firstOperator=" + firstOperator +
                ", secondOperator=" + secondOperator +
                ", operand=" + operand +
                ", result=" + result +
                '}';
    }
    
    private void clearLastInput() {
        if (secondOperator != 0) {
            secondOperator = 0;
        } else {
            firstOperator = 0;
        }
        resetDisplayInput();
    }
    
    private void clearEverything() {
        displayInput = "0";
        firstOperator = 0;
        secondOperator = 0;
        operand = null;
        result = 0;
    }
}
