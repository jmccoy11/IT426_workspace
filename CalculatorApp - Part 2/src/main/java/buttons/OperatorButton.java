/*
 * Jonnathon McCoy
 * 01/26/2018
 * OperatorButton.java
 *
 * This class defines an Operator Button. Operators are whole integer numbers.
 *
 * Many of the Clean Code principles were used in the creation of this project
 * to help reinforce the topics discussed in Clean Code Chapters 1-4. It is
 * quite possible I may be misunderstanding and misusing some of these principles
 * but I am trying.
 */

package buttons;

import enums.ButtonType;
import enums.FunctionType;
import functions.None;

/**
 * This class defines an Operator Button. Operators are whole integer numbers.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class OperatorButton extends CalculatorButton {
    /**
     * Constructor for OperatorButton
     * @param valueAsString String value representation of the value this button is intended for
     */
    public OperatorButton(String valueAsString) {
        super(ButtonType.OPERATOR, valueAsString, FunctionType.OPERATOR);
        setFunction(new None());
    }
}
