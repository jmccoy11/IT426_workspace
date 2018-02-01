/*
 * Jonnathon McCoy
 * 01/20/2018
 * CalculatorController.java
 *
 * This class will contain the Controller that will interact between the Model
 * and the User Interface.
 *
 * Many of the Clean Code principles were used in the creation of this project
 * to help reinforce the topics discussed in Clean Code Chapters 1-4. It is
 * quite possible I may be misunderstanding and misusing some of these principles
 * but I am trying.
 */

package controller;

import buttons.CalculatorButton;
import calculator.Calculator;
import enums.ButtonType;
import enums.FunctionType;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import ui.CalculatorUI;

import java.util.HashMap;

/**
 * This class will contain the Controller that will interact between the Model
 * and the User Interface.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class CalculatorController {
    private final boolean DEBUG_MODE = false;
    
    private Calculator arithmeticModel;
    private HashMap<String, FunctionType> functionsHashMap = new HashMap<>();
    private HashMap<String, CalculatorButton> buttonHashMap = new HashMap<>();
    
    private static final Object[][] functionValues = new Object[][]{
            {"+", FunctionType.ADD}, {"-", FunctionType.SUBTRACT},
            {"*", FunctionType.MULTIPLY}, {"/", FunctionType.DIVIDE},
            {"Enter", FunctionType.ENTER}, {"C", FunctionType.CLEAR},
            {"CE", FunctionType.CLEAR_EVERYTHING}
    };
    
    /**
     * Constructor for CalculatorController.
     * This class will build the functionsHashMap from the functionValues
     */
    public CalculatorController() {
        arithmeticModel = new Calculator();
        for (Object[] functionValue : functionValues) {
            functionsHashMap.put((String) functionValue[0], (FunctionType) functionValue[1]);
        }
    }
    
    /**
     * Add a button to the buttons HashMap to be iterated over later to apply
     * click listeners to the buttons.
     * @param button - CalculatorButton that was created by the UI.
     */
    public void addButton(CalculatorButton button) {
        buttonHashMap.put(button.getValueAsString(), button);
    }
    
    /**
     * Set OnClick Listeners to each of the buttons to handle what the Model will
     * be performing after Click and update the UI appropriately.
     */
    public void setListeners() {
        for (CalculatorButton button : buttonHashMap.values()) {
            button.getButtonLayout().setOnMouseClicked(new EventHandler<MouseEvent>() {
                
                @Override
                public void handle(MouseEvent event) {
                    buttonDebugCheck(button);
    
                    if (isOperator(button)) {
                        arithmeticModel.evaluateOperator(button.getValueAsString());
                    } else if (isOperand(button)) {
                        arithmeticModel.evaluateOperand(button.getFunction());
                    } else if (isTool(button)) {
                        arithmeticModel.evaluateTool(button.getFunctionType());
                    }
                    updateUIDisplayOutput();
                }
            });
        }
    }
    
    /**
     * Return the functionType that belongs to the String symbol
     * @param buttonValue - String of the value in the button.
     * @return - Return FunctionType from enum associated with the symbol.
     */
    public FunctionType getFunctionType(String buttonValue) {
        return functionsHashMap.get(buttonValue);
    }
    
    //Easier to read function for determining if a button is an Operator
    private boolean isOperator(CalculatorButton button) {
        return button.getButtonType().equals(ButtonType.OPERATOR);
    }
    
    //Easier to read function for determining if a button is an Operand
    private boolean isOperand(CalculatorButton button) {
        return button.getButtonType().equals(ButtonType.OPERAND);
    }
    
    //Easier to read function for determining if a button is a Tool
    private boolean isTool(CalculatorButton button) {
        return button.getButtonType().equals(ButtonType.TOOL);
    }
    
    private void updateUIDisplayOutput() {
        CalculatorUI.setUIOutputDisplayText(arithmeticModel.getDisplayOutput());
    }
    
    //For debugging purposes
    private void buttonDebugCheck(CalculatorButton button) {
        if (DEBUG_MODE) {
            System.out.println("Button Pressed: " + button.getValueAsString() + " FunctionType: " +
                    button.getFunctionType());
            System.out.println(arithmeticModel.toString());
        }
    }
}