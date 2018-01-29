package buttons;

import enums.ButtonType;
import enums.FunctionType;
import functions.Add;
import functions.Divide;
import functions.Multiply;
import functions.Subtract;

public class OperandButton extends CalculatorButton {
    
    public OperandButton(String valueAsString, FunctionType functionType) {
        super(ButtonType.OPERAND, valueAsString, functionType);
        if (functionType.equals(FunctionType.ADD)) {
            setFunction(new Add());
        } else if (functionType.equals(FunctionType.SUBTRACT)) {
            setFunction(new Subtract());
        } else if (functionType.equals(FunctionType.MULTIPLY)) {
            setFunction(new Multiply());
        } else if (functionType.equals(FunctionType.DIVIDE)) {
            setFunction(new Divide());
        }
    }
}
