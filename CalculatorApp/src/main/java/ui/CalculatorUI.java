package ui;

import constants.CalculatorProperties;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class CalculatorUI extends Application{
    
    //For part #2 where we will be adding click listeners
    private static ArrayList<Label> buttons = new ArrayList<>();
    
    /**
     * Entry point for the program.
     *
     * @param primaryStage - Stage - Main screen
     */
    @Override
    public void start(Stage primaryStage) {
        setWindowProperties(primaryStage);
        primaryStage.show();
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
        appLayout.getChildren().add(createButtonLayout());
        
        return new Scene(appLayout, CalculatorProperties.APP_MIN_WIDTH, CalculatorProperties.APP_MIN_HEIGHT);
    }
    
    private VBox createAppLayout() {
        VBox appLayout = new VBox();
        appLayout.setAlignment(Pos.CENTER);
        appLayout.setPadding(new Insets(CalculatorProperties.SIDE_PADDING_INSETS));
        return appLayout;
    }
    
    private GridPane createButtonLayout() {
        GridPane buttonGrid = new GridPane();
        setButtonLayoutProperties(buttonGrid);
        addButtons(buttonGrid);
        return buttonGrid;
    }
    
    private void setButtonLayoutProperties(GridPane buttonGrid) {
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setVgap(CalculatorProperties.BUTTON_GAP);
        buttonGrid.setHgap(CalculatorProperties.BUTTON_GAP);
        buttonGrid.getColumnConstraints().addAll(createButtonLayoutColumnConstraints());
    }
    
    private void addButtons(GridPane buttonGrid) {
        createButtons(buttonGrid);
        createOutputDisplay(buttonGrid);
    }
    
    private void createButtons(GridPane buttonGrid) {
        for(int row = 0; row < CalculatorProperties.BUTTON_MAP.length; row++) {
            for(int column = 0; column < CalculatorProperties.BUTTON_MAP[row].length; column++) {
                HBox buttonLayout = new HBox();
                setButtonLayoutProperties(buttonLayout);
            
                Label button = new Label(CalculatorProperties.BUTTON_MAP[row][column]);
                button.setAlignment(Pos.CENTER);
            
                buttonLayout.getChildren().add(button);
            
                if (button.getText().equals("Enter")) {
                    button.setPrefWidth((CalculatorProperties.ButtonProperties.BUTTON_WIDTH +
                            CalculatorProperties.BUTTON_GAP)*2);
                    buttonGrid.add(buttonLayout, column, row, CalculatorProperties.ENTER_BTN_COLSPAN, 1);
                } else if (button.getText().equals("/")) {
                    buttonGrid.add(buttonLayout, column+1, row);
                } else {
                    buttonGrid.add(buttonLayout, column, row);
                }
            
                buttons.add(button);
            }
        }
    }
    
    private void createOutputDisplay(GridPane buttonGrid) {
        HBox outputDisplay = new HBox();
        setOutputDisplayProperties(outputDisplay);
    
        Label outputDisplayText = new Label("Output");
        outputDisplayText.setAlignment(Pos.CENTER_RIGHT);
    
        outputDisplay.getChildren().add(outputDisplayText);
    
        buttonGrid.add(outputDisplay,
                0, CalculatorProperties.OutputDisplayProperties.OUTPUT_DISPLAY_ROW_INDEX,
                CalculatorProperties.OutputDisplayProperties.OUTPUT_DISPLAY_COL_SPAN, 1);
    }
    
    private void setButtonLayoutProperties(HBox buttonLayout) {
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setBackground(CalculatorProperties.ButtonProperties.BUTTON_BACKGROUND);
        buttonLayout.setPrefHeight(CalculatorProperties.ButtonProperties.BUTTON_HEIGHT);
        buttonLayout.setPrefWidth(CalculatorProperties.ButtonProperties.BUTTON_WIDTH);
        buttonLayout.setBorder(CalculatorProperties.ButtonProperties.BUTTON_BORDER);
    }
    
    private void setOutputDisplayProperties(HBox outputDisplay) {
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
