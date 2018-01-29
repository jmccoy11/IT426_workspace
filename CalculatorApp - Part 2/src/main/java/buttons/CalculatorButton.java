package buttons;

import enums.ButtonType;
import enums.FunctionType;
import functions.None;
import interfaces.EvaluateExpression;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public abstract class CalculatorButton {
    
    private ButtonType buttonType;
    private String valueAsString;
    private FunctionType functionType;
    private EvaluateExpression function;
    
    private HBox buttonLayout;
    private Label buttonText;
    
    public CalculatorButton(ButtonType buttonType, String valueAsString, FunctionType functionType) {
        this.buttonType = buttonType;
        this.valueAsString = valueAsString;
        this.functionType = functionType;
        function = new None();
    }
    
    public ButtonType getButtonType() {
        return buttonType;
    }
    
    public String getValueAsString() {
        return valueAsString;
    }
    
    public FunctionType getFunctionType() {
        return functionType;
    }
    
    public EvaluateExpression getFunction() {
        return function;
    }
    
    public void setFunction(EvaluateExpression function) {
        this.function = function;
    }
    
    public HBox getButtonLayout() {
        return buttonLayout;
    }
    
    public void setButtonLayout(HBox buttonLayout) {
        this.buttonLayout = buttonLayout;
    }
    
    public Label getButtonText() {
        return buttonText;
    }
    
    public void setButtonText (String buttonText) {
        Label label = new Label(buttonText);
        label.setAlignment(Pos.CENTER);
        this.buttonText = label;
    }
}
