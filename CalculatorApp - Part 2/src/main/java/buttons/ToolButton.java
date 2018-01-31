/*
 * Jonnathon McCoy
 * 01/26/2018
 * ToolButton.java
 *
 * This class defines an Tool button. Tools are non-arithmetic functions that
 * cause the Calculator to change state.
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
 * This class defines an Tool button. Tools are non-arithmetic functions that
 * cause the Calculator to change state.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class ToolButton extends CalculatorButton {
    
    /**
     * Constructor for ToolButton
     * @param valueAsString - String value representation of the value this button is intended for
     * @param functionType - FunctionType from enum
     */
    public ToolButton(String valueAsString, FunctionType functionType) {
        super(ButtonType.TOOL, valueAsString, functionType);
        setFunction(new None());
    }
}
