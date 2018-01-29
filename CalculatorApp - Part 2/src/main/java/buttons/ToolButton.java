package buttons;

import enums.ButtonType;
import enums.FunctionType;
import functions.None;

public class ToolButton extends CalculatorButton {
    
    public ToolButton(String valueAsString, FunctionType function) {
        super(ButtonType.TOOL, valueAsString, function);
        setFunction(new None());
    }
}
