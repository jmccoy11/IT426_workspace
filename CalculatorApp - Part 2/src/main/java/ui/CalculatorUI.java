/*
 * Jonnathon McCoy
 * 01/20/2018
 * CalculatorUI.java
 *
 * This class sets up the User Interface for the Calculator App
 *
 * Many of the Clean Code principles were used in the creation of this project
 * to help reinforce the topics discussed in Clean Code Chapters 1-4. It is
 * quite possible I may be misunderstanding and misusing some of these principles
 * but I am trying.
 */

package ui;

import buttons.CalculatorButton;
import buttons.OperandButton;
import buttons.OperatorButton;
import buttons.ToolButton;
import constants.CalculatorProperties;
import controller.CalculatorController;
import enums.ButtonType;
import enums.FunctionType;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.LinkedList;

/**
 * This class sets up the User Interface for the Calculator App
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class CalculatorUI extends Application{
    private CalculatorController controller;
    private HBox outputDisplay;
    private static Label outputDisplayText;
    private GridPane buttonGrid;
    
    private final Object[][][] BUTTON_DATA = new Object[][][]{
            {
                {"C", ButtonType.TOOL}, {"CE", ButtonType.TOOL}
            },
            {
                {"7", ButtonType.OPERATOR}, {"8", ButtonType.OPERATOR}, {"9", ButtonType.OPERATOR},
                    {"+", ButtonType.OPERAND}
            },
            {
                {"4", ButtonType.OPERATOR}, {"5", ButtonType.OPERATOR}, {"6", ButtonType.OPERATOR},
                    {"-", ButtonType.OPERAND}
            },
            {
                {"1", ButtonType.OPERATOR}, {"2", ButtonType.OPERATOR}, {"3", ButtonType.OPERATOR},
                    {"*", ButtonType.OPERAND}
            },
            {
                {"0", ButtonType.OPERATOR}, {"Enter", ButtonType.TOOL}, {"/", ButtonType.OPERAND}
            }
    };
    
    /**
     * Entry point for the program.
     *
     * @param primaryStage - Stage - Main screen
     */
    @Override
    public void start(Stage primaryStage) {
        controller = new CalculatorController();
        setWindowProperties(primaryStage);
        controller.setListeners();
        primaryStage.show();
    }
    
    public static String getOutputDisplayText() {
        return outputDisplayText.getText();
    }
    
    public static void setOutputDisplayText(String newText) {
        outputDisplayText.setText(newText);
    }
    
    private void setWindowProperties(Stage appWindow) {
        appWindow.setTitle("Calculator");
        appWindow.setScene(createCalculatorLayout());
        setWindowWidthAndHeight(appWindow);
    }
    
    private void setWindowWidthAndHeight(Stage appWindow) {
        appWindow.setMinWidth(CalculatorProperties.APP_MIN_WIDTH);
        appWindow.setMinHeight(CalculatorProperties.APP_MIN_HEIGHT);
        appWindow.setMaxWidth(CalculatorProperties.APP_MAX_WIDTH);
        appWindow.setMaxHeight(CalculatorProperties.APP_MAX_HEIGHT);
    }
    
    private Scene createCalculatorLayout() {
        VBox appLayout = createAppLayout();
        appLayout.setSpacing(CalculatorProperties.ELEMENT_SPACING);
        
        createButtonLayout();
        appLayout.getChildren().add(buttonGrid);
        
        createOutputDisplay();
        appLayout.getChildren().add(outputDisplay);
        
        return new Scene(appLayout, CalculatorProperties.APP_MIN_WIDTH, CalculatorProperties.APP_MIN_HEIGHT);
    }
    
    private VBox createAppLayout() {
        VBox appLayout = new VBox();
        appLayout.setAlignment(Pos.CENTER);
        appLayout.setPadding(new Insets(CalculatorProperties.SIDE_PADDING_INSETS));
        return appLayout;
    }
    
    private void createButtonLayout() {
        buttonGrid = new GridPane();
        setButtonLayoutProperties();
        createButtonsGrid();
    }
    
    private void setButtonLayoutProperties() {
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setVgap(CalculatorProperties.BUTTON_GAP);
        buttonGrid.setHgap(CalculatorProperties.BUTTON_GAP);
        buttonGrid.getColumnConstraints().addAll(createButtonLayoutColumnConstraints());
    }
    
    private void createButtonsGrid() {
        for(int row = 0; row < BUTTON_DATA.length; row++) {
            for(int column = 0; column < BUTTON_DATA[row].length; column++) {
                String buttonValue = (String) BUTTON_DATA[row][column][0];
                ButtonType buttonType = (ButtonType) BUTTON_DATA[row][column][1];
                
                CalculatorButton button = createButton(buttonValue, buttonType);
                
                placeButton(button, row, column);
                
                controller.addButton(button);
            }
        }
    }
    
    private CalculatorButton createButton(String buttonValue, ButtonType buttonType) {
        CalculatorButton button = evaluateAndCreateButtonType(buttonValue, buttonType);
        button.setButtonLayout(createButtonVisual(buttonValue));
        button.setButtonText(buttonValue);
        
        return button;
    }
    
    private CalculatorButton evaluateAndCreateButtonType(String buttonValue, ButtonType buttonType) {
        CalculatorButton button = null;
    
        if (buttonType.equals(ButtonType.TOOL)) {
            button = new ToolButton(buttonValue, controller.getFunctionType(buttonValue));
        } else if (buttonType.equals(ButtonType.OPERAND)) {
            button = new OperandButton(buttonValue, controller.getFunctionType(buttonValue));
        } else {
            button = new OperatorButton(buttonValue, Integer.valueOf(buttonValue));
        }
        
        return button;
    }
    
    
    private HBox createButtonVisual(String buttonLabel) {
        HBox buttonLayout = new HBox();
        setButtonLayoutProperties(buttonLayout);
        
        // Each button is created from the the 2D BUTTON_MAP array
        Label button = new Label(buttonLabel);
        button.setAlignment(Pos.CENTER);
        
        buttonLayout.getChildren().add(button);
        
        return buttonLayout;
    }
    
    private void placeButton(CalculatorButton button, int row, int column) {
        //Conditionals to account for Enter being twice the size as a normal button and the "/" button needing
        //to be placed in the last column.
        if (button.getFunctionType().equals(FunctionType.ENTER)) {
            button.getButtonText().setPrefWidth((CalculatorProperties.ButtonProperties.BUTTON_WIDTH +
                    CalculatorProperties.BUTTON_GAP)*2);
            buttonGrid.add(button.getButtonLayout(), column, row,
                    CalculatorProperties.ButtonProperties.ENTER_BTN_COLSPAN, 1);
        } else if (button.getFunctionType().equals(FunctionType.CLEAR)) {
            buttonGrid.add(button.getButtonLayout(), CalculatorProperties.ButtonProperties.CLEAR_COL_INDEX, row);
        } else if (button.getFunctionType().equals(FunctionType.CLEAR_EVERYTHING)) {
            buttonGrid.add(button.getButtonLayout(), CalculatorProperties.ButtonProperties.CLEAR_EVERYTHING_COL_INDEX,
                    row);
        } else if (button.getFunctionType().equals(FunctionType.DIVIDE)) {
            buttonGrid.add(button.getButtonLayout(), column + 1, row);
        } else {
            buttonGrid.add(button.getButtonLayout(), column, row);
        }
    }
    
    // The output display changes when buttons are pressed and will display the results of an arithmetic function
    private void createOutputDisplay() {
        outputDisplay = new HBox();
        setOutputDisplayProperties();
    
        outputDisplayText = new Label("0");
        outputDisplayText.setAlignment(Pos.CENTER_RIGHT);
    
        outputDisplay.getChildren().add(outputDisplayText);
    }
    
    private void setButtonLayoutProperties(HBox buttonLayout) {
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setBackground(CalculatorProperties.ButtonProperties.BUTTON_BACKGROUND);
        buttonLayout.setPrefHeight(CalculatorProperties.ButtonProperties.BUTTON_HEIGHT);
        buttonLayout.setPrefWidth(CalculatorProperties.ButtonProperties.BUTTON_WIDTH);
        buttonLayout.setBorder(CalculatorProperties.ButtonProperties.BUTTON_BORDER);
    }
    
    private void setOutputDisplayProperties() {
        outputDisplay.setBackground(CalculatorProperties.OutputDisplayProperties.OUTPUT_DISPLAY_BACKGROUND);
        outputDisplay.setPrefHeight(CalculatorProperties.OutputDisplayProperties.OUTPUT_DISPLAY_HEIGHT);
        outputDisplay.setAlignment(Pos.CENTER_RIGHT);
        outputDisplay.setPadding(CalculatorProperties.OutputDisplayProperties.OUTPUT_DISPLAY_PADDING);
        outputDisplay.setBorder(CalculatorProperties.OutputDisplayProperties.OUTPUT_DISPLAY_BORDER);
    }
    
    private Collection<ColumnConstraints> createButtonLayoutColumnConstraints() {
        Collection<ColumnConstraints> columnConstraints = new LinkedList<>();
        for(int i = 0; i < CalculatorProperties.BUTTON_GRID_COLUMNS; i++) {
            ColumnConstraints columnConstraint = new ColumnConstraints();
            columnConstraint.setPercentWidth(CalculatorProperties.COLUMN_CONSTRAINT_WIDTH_PERCENT);
            columnConstraints.add(columnConstraint);
        }
        return columnConstraints;
    }
}
