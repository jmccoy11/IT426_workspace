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
                    System.out.println("Button Pressed: " + button.getValueAsString() + " FunctionType: " +
                        button.getFunctionType());
    
                    if (button.getButtonType().equals(ButtonType.OPERATOR)) {
                        arithmeticModel.evaluateOperator(button.getValueAsString());
                        CalculatorUI.setOutputDisplayText(arithmeticModel.getDisplayInput());
                    } else if (button.getButtonType().equals(ButtonType.OPERAND)) {
                        arithmeticModel.evaluateOperand(button.getFunction());
                        CalculatorUI.setOutputDisplayText(arithmeticModel.getDisplayInput());
                        arithmeticModel.resetDisplayInput();
                    } else if (button.getButtonType().equals(ButtonType.TOOL)) {
                        arithmeticModel.evaluateTool(button.getFunctionType());
                        CalculatorUI.setOutputDisplayText(arithmeticModel.getDisplayInput());
                        arithmeticModel.resetDisplayInput();
                    }
    
                    System.out.println(arithmeticModel.toString());
                }
            });
        }
    }
    
    public FunctionType getFunctionType(String buttonValue) {
        return functionsHashMap.get(buttonValue);
    }
}