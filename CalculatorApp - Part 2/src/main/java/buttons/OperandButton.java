/*
 * Jonnathon McCoy
 * 01/26/2018
 * OperandButton.java
 *
 * This class defines an Operand Button. Operands are functions like add, subtract,
 * multiply, and divide.
 *
 * Many of the Clean Code principles were used in the creation of this project
 * to help reinforce the topics discussed in Clean Code Chapters 1-4. It is
 * quite possible I may be misunderstanding and misusing some of these principles
 * but I am trying.
 */

package buttons;

import enums.ButtonType;
import enums.FunctionType;
import functions.Add;
import functions.Divide;
import functions.Multiply;
import functions.Subtract;

/**
 * This class defines an Operand Button. Operands are functions like add, subtract,
 * multiply, and divide.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class OperandButton extends CalculatorButton {
    
    /**
     * Constructor for an OperandButton
     * @param valueAsString - String value representation of the value this button is intended for
     * @param functionType - FunctionType from enum
     */
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
