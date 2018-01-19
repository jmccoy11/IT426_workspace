package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class CalculatorUI extends Application{
    
    private static final int SIDE_PADDING_INSETS = 20;
    private static final int APP_MIN_WIDTH = 300;
    private static final int APP_MIN_HEIGHT = 400;
    private static final int COLUMN_CONSTRAINT_WIDTH_PERCENT = 25;
    private static final int BUTTON_GAP = 10;
    private static final int BUTTON_GRID_COLUMNS = 4;
    private static final int ENTERBUTTON_COLSPAN = 2;
    private static final String[][] BUTTON_MAP = new String[][]{
        {"7", "8", "9", "+"},
        {"4", "5", "6", "-"},
        {"1", "2", "3", "*"},
        {"0", "Enter", "/"}
    };
    private static ArrayList<Button> buttons = new ArrayList<>();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        setWindowProperties(primaryStage);
        primaryStage.show();
    }
    
    private void setWindowProperties(Stage appWindow) {
        appWindow.setTitle("Calculator");
        appWindow.setScene(getCalculatorLayout());
        appWindow.setMinWidth(APP_MIN_WIDTH);
        appWindow.setMinHeight(APP_MIN_HEIGHT);
    }
    
    private Scene getCalculatorLayout() {
        //VBox appLayout = getApplicationWindow();
        //appLayout.getChildren().addAll(getLayouts());
        
        VBox appLayout = new VBox();
        appLayout.setAlignment(Pos.CENTER);
        appLayout.setPadding(new Insets(SIDE_PADDING_INSETS));
        
        //Create Button layout
        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setVgap(BUTTON_GAP);
        buttonGrid.setHgap(BUTTON_GAP);
    
        buttonGrid.getColumnConstraints().addAll(createButtonLayoutColumnConstraints());
        
        int buttonWidth = 50;
        int buttonHeight = 50;
        int gridRows = 4;
        int gridColumns = 4;
        Color buttonColor = Color.gray(0.9);
        Color buttonBorderColor = Color.gray(0.3);
        CornerRadii buttonCornerRadii = new CornerRadii(5);
        BorderWidths buttonBorderWidth = new BorderWidths(1);
        
        for(int row = 0; row < BUTTON_MAP.length; row++) {
            
            for(int column = 0; column < BUTTON_MAP[row].length; column++) {
                
                HBox buttonLayout = new HBox();
                buttonLayout.setAlignment(Pos.CENTER);
    
                Button button = new Button(BUTTON_MAP[row][column]);
                button.setAlignment(Pos.CENTER_RIGHT);
//                button.setBackground(new Background(new BackgroundFill(buttonColor,
//                        buttonCornerRadii, Insets.EMPTY)));
                button.setPrefHeight(buttonHeight);
                button.setPrefWidth(buttonWidth);
                button.setBorder(new Border(new BorderStroke(buttonBorderColor,
                        BorderStrokeStyle.SOLID, buttonCornerRadii,
                        buttonBorderWidth)));
                
                buttonLayout.getChildren().add(button);
                
                if (button.getText().equals("Enter")) {
                    button.setPrefWidth((buttonWidth+BUTTON_GAP)*2);
                    buttonGrid.add(buttonLayout, column, row, ENTERBUTTON_COLSPAN, 1);
                } else if (button.getText().equals("/")) {
                    buttonGrid.add(buttonLayout, column+1, row);
                } else {
                    buttonGrid.add(buttonLayout, column, row);
                }
                
                buttons.add(button);
            }
        }
    
        HBox outputDisplay = new HBox();
        outputDisplay.setBackground(new Background(new BackgroundFill(Color.WHITE,
                                            buttonCornerRadii, Insets.EMPTY)));
        outputDisplay.setPrefHeight(buttonHeight);
        outputDisplay.setAlignment(Pos.CENTER_RIGHT);
        outputDisplay.setPadding(new Insets(0, 20, 0, 20));
        outputDisplay.setBorder(new Border(new BorderStroke(buttonBorderColor,
                                    BorderStrokeStyle.SOLID, buttonCornerRadii,
                                    buttonBorderWidth)));
        
        Label outputDisplayText = new Label("Output");
        outputDisplayText.setAlignment(Pos.CENTER_RIGHT);
    
        outputDisplay.getChildren().add(outputDisplayText);
        buttonGrid.add(outputDisplay, 0, 4, 4, 1);
    
        /*for (Button button : buttons) {
            button.addEventHandler(javafx.event.ActionEvent.ACTION, new javafx.event.EventHandler<javafx.event.ActionEvent>() {
                @Override
                public void handle(javafx.event.ActionEvent event) {
                    outputDisplayText.setText(outputDisplayText.getText() + button.getText());
                }
            });
        }*/
        
        appLayout.getChildren().add(buttonGrid);
        
        return new Scene(appLayout, APP_MIN_WIDTH, APP_MIN_HEIGHT);
    }
    /*
    private VBox getApplicationWindow() {
        VBox windowLayout = new VBox();
        windowLayout.setAlignment(Pos.CENTER);
        windowLayout.setPadding(new Insets(SIDE_PADDING_INSETS));
    
        return windowLayout;
    }
    
    private GridPane getButtonLayout() {
        GridPane buttonLayout = createButtonLayout();
        
        return buttonLayout;
    }
    
    private GridPane createButtonLayout() {
        GridPane buttonGrid = new GridPane();
        setButtonLayoutProperties(buttonGrid);
        addButtons(buttonGrid);
        return buttonGrid;
    }
    
    private void setButtonLayoutProperties(GridPane buttonGrid) {
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setVgap(BUTTON_GAP);
        buttonGrid.setHgap(BUTTON_GAP);
        buttonGrid.getColumnConstraints().addAll(createButtonLayoutColumnConstraints());
    }
    
    private void addButtons(GridPane buttonGrid) {
    
    }
    
    private Button createButton(String value) {
        Button button = new Button(value);
        return button;
    }
    */
    
    private Collection<ColumnConstraints> createButtonLayoutColumnConstraints() {
        Collection<ColumnConstraints> columnConstraints = new LinkedList<>();
        for(int i = 0; i < BUTTON_GRID_COLUMNS; i++) {
            ColumnConstraints columnConstraint = new ColumnConstraints();
            columnConstraint.setPercentWidth(COLUMN_CONSTRAINT_WIDTH_PERCENT);
            columnConstraints.add(columnConstraint);
        }
        return columnConstraints;
    }
    
    /*private Collection<Node> getLayouts() {
        Collection<Node> layouts = new LinkedList<>();
        layouts.add(getButtonLayout());
        return layouts;
    }*/
}
