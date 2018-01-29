package buttons;

import enums.ButtonType;
import enums.FunctionType;
import functions.None;

public class OperatorButton extends CalculatorButton {
    private final Integer value;
    
    public OperatorButton(String valueAsString, Integer value) {
        super(ButtonType.OPERATOR, valueAsString, FunctionType.OPERATOR);
        this.value = value;
        setFunction(new None());
    }
    
    public Integer getValue() {
        return value;
    }
}
