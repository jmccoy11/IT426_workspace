/*
 * Jonnathon McCoy
 * 01/26/2018
 * CalculatorButton.java
 *
 * This class defines the abstract class Calculator button from which other buttons
 * extend from so that methods may be called from a child of CalculatorButton.
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
import interfaces.EvaluateExpressionInterface;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * This class defines the abstract class Calculator button from which other buttons
 * extend from so that methods may be called from a child of CalculatorButton.
 * @author Jonnathon McCoy
 * @version 1.0
 */
public abstract class CalculatorButton {
    
    private ButtonType buttonType;
    private String valueAsString;
    private FunctionType functionType;
    private EvaluateExpressionInterface function;
    
    private HBox buttonLayout;
    private Label buttonText;
    
    /**
     * IntelliJ said I could make this package-private (hence no public scope identifier)
     *
     * Constructor for a CalculatorButton.
     *
     * @param buttonType - ButtonType from enum
     * @param valueAsString - String value representation of the value this button is intended for
     * @param functionType - FunctionType from enum
     */
    CalculatorButton(ButtonType buttonType, String valueAsString, FunctionType functionType) {
        this.buttonType = buttonType;
        this.valueAsString = valueAsString;
        this.functionType = functionType;
        function = new None();
    }
    
    /**
     * Getter for buttonType
     * @return buttonType
     */
    public ButtonType getButtonType() {
        return buttonType;
    }
    
    /**
     * Getter for valueAsString.
     * @return valueAsString
     */
    public String getValueAsString() {
        return valueAsString;
    }
    
    /**
     * Getter for functionType
     * @return functionType
     */
    public FunctionType getFunctionType() {
        return functionType;
    }
    
    /**
     * Getter for function
     * @return function grouped by its interface, EvaluateExpressionInterface
     */
    public EvaluateExpressionInterface getFunction() {
        return function;
    }
    
    // IntelliJ said I could make this package-private (hence no public scope identifier)
    
    /**
     * The buttons that extend from this class will create different functions (Add, Subtract,
     * Multiply, Divide) so the constructor will set it to None automatically. The constructors
     * of the other Buttons will call setFunction to insert the proper function for that
     * button.
     * @param function - Functions are grouped by their Evaluate Expression Interface such that
     *                 evaluateExpression() can be called from each called function of a button.
     */
    void setFunction(EvaluateExpressionInterface function) {
        this.function = function;
    }
    
    /**
     * Getter for the buttonLayout
     * @return buttonLayout
     */
    public HBox getButtonLayout() {
        return buttonLayout;
    }
    
    /**
     * Setter for buttonLayout
     * @param buttonLayout - HBox layout for the Button
     */
    public void setButtonLayout(HBox buttonLayout) {
        this.buttonLayout = buttonLayout;
    }
    
    /**
     * Get the text inside the Button.
     * @return buttonText as a Label
     */
    public Label getButtonText() {
        return buttonText;
    }
    
    /**
     * Setter for buttonText.
     * @param buttonText - String text to be placed in the label.
     */
    public void setButtonText (String buttonText) {
        //this ensures that the Label has been created by just instantiating a new Label
        this.buttonText = new Label(buttonText);
        this.buttonText.setAlignment(Pos.CENTER);
    }
}
