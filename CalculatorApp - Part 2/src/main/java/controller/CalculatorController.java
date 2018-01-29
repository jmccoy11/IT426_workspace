package controller;

import buttons.CalculatorButton;
import calculator.Calculator;
import enums.ButtonType;
import enums.FunctionType;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import ui.CalculatorUI;

import java.util.HashMap;

public class CalculatorController {
    private final boolean DEBUG_MODE = true;
    
    private Calculator arithmeticModel;
    private HashMap<String, FunctionType> functionsHashMap = new HashMap<>();
    private HashMap<String, CalculatorButton> buttonHashMap = new HashMap<>();
    
    private static final Object[][] functionValues = new Object[][]{
            {"+", FunctionType.ADD}, {"-", FunctionType.SUBTRACT},
            {"*", FunctionType.MULTIPLY}, {"/", FunctionType.DIVIDE},
            {"Enter", FunctionType.ENTER}, {"C", FunctionType.CLEAR},
            {"CE", FunctionType.CLEAR_EVERYTHING}
    };
    
    public CalculatorController() {
        arithmeticModel = new Calculator();
        for (Object[] functionValue : functionValues) {
            functionsHashMap.put((String) functionValue[0], (FunctionType) functionValue[1]);
        }
    }
    
    public void addButton(CalculatorButton button) {
        buttonHashMap.put(button.getValueAsString(), button);
    }
    
    public void setListeners() {
        for (CalculatorButton button : buttonHashMap.values()) {
            button.getButtonLayout().setOnMouseClicked(new EventHandler<MouseEvent>() {
                
                @Override
                public void handle(MouseEvent event) {
                    if (DEBUG_MODE) {
                        System.out.println("Button Pressed: " + button.getValueAsString() + " FunctionType: " +
                                button.getFunctionType());
                        System.out.println(arithmeticModel.toString());
                    }
    
                    if (isOperator(button)) {
                        arithmeticModel.evaluateOperator(button.getValueAsString());
                        updateDisplayOutput();
                    } else if (isOperand(button)) {
                        arithmeticModel.evaluateOperand(button.getFunction());
                        updateAndResetDisplayOutput();
                    } else if (isTool(button)) {
                        arithmeticModel.evaluateTool(button.getFunctionType());
                        updateAndResetDisplayOutput();
                    }
                }
            });
        }
    }
    
    public FunctionType getFunctionType(String buttonValue) {
        return functionsHashMap.get(buttonValue);
    }
    
    private boolean isOperator(CalculatorButton button) {
        return button.getButtonType().equals(ButtonType.OPERATOR);
    }
    
    private boolean isOperand(CalculatorButton button) {
        return button.getButtonType().equals(ButtonType.OPERAND);
    }
    
    private boolean isTool(CalculatorButton button) {
        return button.getButtonType().equals(ButtonType.TOOL);
    }
    
    private void updateDisplayOutput() {
        CalculatorUI.setOutputDisplayText(arithmeticModel.getDisplayOutput());
    }
    
    private void updateAndResetDisplayOutput() {
        CalculatorUI.setOutputDisplayText(arithmeticModel.getDisplayOutput());
        arithmeticModel.resetDisplayOutput();
    }
}