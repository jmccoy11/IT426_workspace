/*
 * Jonnathon McCoy
 * 01/26/2018
 * TestDriver.java
 *
 * Unit Testing for the Model since I didn't have time to remember how to use JUnit.
 *
 * This class does NOT follow clean code principles as it's a throw away class.
 */

package testDriver;

import calculator.Calculator;
import enums.FunctionType;
import functions.*;
import interfaces.EvaluateExpressionInterface;

/**
 * Unit Testing for the Model since I didn't have time to remember how to use JUnit.
 *
 * This class does NOT follow clean code principles as it's a throw away class.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class TestDriver {
    
    /**
     * Entry point for the TestDriver.
     * @param args - Command Line arguments.
     */
    public static void main(String[] args) {
        Calculator model = new Calculator();
        
        System.out.println("--------Testing Constructor--------");
        System.out.println("Testing the Model Constructor: ");
        System.out.println(model.toString());
        
        System.out.println();
        System.out.println("--------Testing Operators--------");
        System.out.println("Entering 1 as input: ");
        model.evaluateOperator("1");
        System.out.println("Expected: 1 | Actual: " + model.getDisplayOutput());
    
        System.out.println("Entering 2 as input: ");
        model.evaluateOperator("2");
        System.out.println("Expected: 12 | Actual: " + model.getDisplayOutput());
        model.evaluateTool(FunctionType.CLEAR_EVERYTHING);
    
        System.out.println("Testing for when input is already in firstOperator: ");
        model.evaluateOperand(new Add());
        model.evaluateOperator("1");
        System.out.println("Expected: 13 | Actual: " + model.getDisplayOutput());
        model.evaluateTool(FunctionType.CLEAR_EVERYTHING);
        
        System.out.println();
        System.out.println("--------Testing Operand Functions--------");
        System.out.println("Testing add function");
        EvaluateExpressionInterface function = new Add();
        System.out.println("Added 1 + 2");
        System.out.println("Expected: 3 | Actual: " + function.evaluateExpression(1, 2));
        System.out.println();
        System.out.println("Testing subtract function");
        function = new Subtract();
        System.out.println("Added 5 - 2");
        System.out.println("Expected: 3 | Actual: " + function.evaluateExpression(5, 2));
        System.out.println();
        System.out.println("Testing multiply function");
        function = new Multiply();
        System.out.println("Added 2 * 3");
        System.out.println("Expected: 6 | Actual: " + function.evaluateExpression(2, 3));
        System.out.println();
        System.out.println("Testing divide function");
        function = new Divide();
        System.out.println("Added 6 / 2");
        System.out.println("Expected: 3 | Actual: " + function.evaluateExpression(6, 2));
        System.out.println();
        System.out.println("Testing divide by zero");
        function = new Divide();
        System.out.println("Added 6 / 0");
        System.out.println("Expected: 0 | Actual: " + function.evaluateExpression(6, 0));
    
        System.out.println();
        System.out.println("--------Testing Calculator Results--------");
        System.out.println("Inserting 10 into input 1");
        model.evaluateOperator("1");
        System.out.println(model.toString());
        model.evaluateOperator("0");
        System.out.println(model.toString());
        System.out.println("Pressing Add:");
        model.evaluateOperand(new Add());
        System.out.println(model.toString());
        System.out.println();
        
        System.out.println("Inserting 20 into input 2");
        model.evaluateOperator("2");
        System.out.println(model.toString());
        model.evaluateOperator("0");
        System.out.println(model.toString());
        System.out.println("Pressing Add:");
        model.evaluateOperand(new Add());
        System.out.println(model.toString());
        System.out.println("Added 10 + 20");
        System.out.println("Expected: 30 | Actual: " + model.getDisplayOutput());
        System.out.println();
        
        System.out.println("Adding 10");
        //Controller should reset the display after any other button is pressed except enter
        model.resetDisplayOutput();
        model.evaluateOperator("1");
        System.out.println(model.toString());
        model.evaluateOperator("0");
        System.out.println(model.toString());
        System.out.println("Pressing Add:");
        model.evaluateOperand(new Add());
        System.out.println(model.toString());
        System.out.println("Added 30 + 10");
        System.out.println("Expected: 40 | Actual: " + model.getDisplayOutput());
        System.out.println();
    
        System.out.println("Adding 20");
        //Controller should reset the display after any other button is pressed except enter
        model.resetDisplayOutput();
        model.evaluateOperator("2");
        System.out.println(model.toString());
        model.evaluateOperator("0");
        System.out.println(model.toString());
        System.out.println("Pressing Enter:");
        model.evaluateTool(FunctionType.ENTER);
        System.out.println(model.toString());
        System.out.println("Added 40 + 20");
        System.out.println("Expected: 60 | Actual: " + model.getDisplayOutput());
        System.out.println();
    
        System.out.println("Subtracting 30");
        // Controller should reset the display after any other button is pressed except enter
        model.resetDisplayOutput();
        System.out.println("Pressing Subtract:");
        model.evaluateOperand(new Subtract());
        System.out.println(model.toString());
        model.evaluateOperator("3");
        System.out.println(model.toString());
        model.evaluateOperator("0");
        System.out.println(model.toString());
        System.out.println("Pressing Enter:");
        model.evaluateTool(FunctionType.ENTER);
        System.out.println(model.toString());
        System.out.println("Subtracted 60 - 30");
        System.out.println("Expected: 30 | Actual: " + model.getDisplayOutput());
        System.out.println();
    
        System.out.println("Multiplying by 2");
//        //Controller should reset the display after any other button is pressed except enter
        model.resetDisplayOutput();
        System.out.println("Pressing Multiply:");
        model.evaluateOperand(new Multiply());
        System.out.println("Entering 2");
        model.evaluateOperator("2");
        System.out.println(model.toString());
        System.out.println("Pressing Enter:");
        model.evaluateTool(FunctionType.ENTER);
        System.out.println(model.toString());
        System.out.println("Multiplied 30 * 2");
        System.out.println("Expected: 60 | Actual: " + model.getDisplayOutput());
        System.out.println();
    
        System.out.println("Dividing by 5");
//        //Controller should reset the display after any other button is pressed except enter
        model.resetDisplayOutput();
        System.out.println("Pressing Divide:");
        model.evaluateOperand(new Divide());
        System.out.println("Entering 5");
        model.evaluateOperator("5");
        System.out.println(model.toString());
        System.out.println("Pressing Enter:");
        model.evaluateTool(FunctionType.ENTER);
        System.out.println(model.toString());
        System.out.println("Divided 60 / 5");
        System.out.println("Expected: 12 | Actual: " + model.getDisplayOutput());
        System.out.println();
    
        System.out.println("Dividing by 0");
//        //Controller should reset the display after any other button is pressed except enter
        model.resetDisplayOutput();
        System.out.println("Pressing Divide:");
        model.evaluateOperand(new Divide());
        System.out.println("Entering 0");
        model.evaluateOperator("0");
        System.out.println(model.toString());
        System.out.println("Pressing Enter:");
        model.evaluateTool(FunctionType.ENTER);
        System.out.println(model.toString());
        System.out.println("Multiplied 12 / 0");
        System.out.println("Expected: 0 | Actual: " + model.getDisplayOutput());
        System.out.println();
    }
}
