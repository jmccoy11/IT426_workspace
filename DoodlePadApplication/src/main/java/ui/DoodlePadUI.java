package ui;

import adapters.CircleAdapter;
import adapters.LineAdapter;
import adapters.RectangleAdapter;
import adapters.TriangleAdapter;
import drawing.IShape;
import drawing.SavedShapes;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import shapes.Triangle;

import java.util.Random;

public class DoodlePadUI extends Application{

    private final double WINDOW_MIN_WIDTH = 800;
    private final double WINDOW_MIN_HEIGHT = 600;
    private final double WINDOW_MAX_WIDTH = 800;
    private final double WINDOW_MAX_HEIGHT = 600;
    public static final double CANVAS_INIT_WIDTH = 770;
    public static final double CANVAS_INIT_HEIGHT = 490;

    private Stage primaryStage;
    private Canvas canvas;
    private SavedShapes savedShapes;
    Random random = new Random();

    private final int TOGGLE_BTN_SIZE = 30;

    //pollable fields
    CheckBox fillCbx;
    TextField thicknessEntryField;
    Slider thicknessSlider;

    public DoodlePadUI() {
        savedShapes = new SavedShapes();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setMinWidth(WINDOW_MIN_WIDTH);
        primaryStage.setMinHeight(WINDOW_MIN_HEIGHT);
        primaryStage.setMaxWidth(WINDOW_MAX_WIDTH);
        primaryStage.setMaxHeight(WINDOW_MAX_HEIGHT);

        primaryStage.setTitle("DoodlePad");
        primaryStage.setScene(getScene());
        primaryStage.show();

        savedShapes.drawShapes(canvas.getGraphicsContext2D());
    }

    private Scene getScene() {
        VBox window = new VBox();
        //set paddings and such
        window.setStyle("-fx-padding: 5;" +
                "-fx-spacing: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: red;");
        window.prefWidthProperty().bind(primaryStage.widthProperty());
        window.prefHeightProperty().bind(primaryStage.heightProperty());






        HBox toolbar = new HBox();
        toolbar.setStyle("-fx-padding: 5;" +
                "-fx-spacing: 10;" +
                "-fx-pref-height: 50;" +
                "-fx-alignment: center_left;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: blue;");

        ToggleGroup buttonToggleGroup = new ToggleGroup();

        ToggleButton circleChoiceBtn = new ToggleButton("", new Circle(10, Color.GREEN));
        circleChoiceBtn.setMinSize(TOGGLE_BTN_SIZE, TOGGLE_BTN_SIZE);
        circleChoiceBtn.setToggleGroup(buttonToggleGroup);

        buttonToggleGroup.selectToggle(circleChoiceBtn);

        ToggleButton rectangleChoiceBtn = new ToggleButton("", new Rectangle(20, 15));
        rectangleChoiceBtn.setMinSize(TOGGLE_BTN_SIZE, TOGGLE_BTN_SIZE);
        rectangleChoiceBtn.setToggleGroup(buttonToggleGroup);

        ToggleButton triangleChoiceBtn = new ToggleButton("Triangle");
        triangleChoiceBtn.setMinSize(TOGGLE_BTN_SIZE, TOGGLE_BTN_SIZE);
        triangleChoiceBtn.setToggleGroup(buttonToggleGroup);

        ToggleButton lineChoiceBtn = new ToggleButton("", new Line(0, 0, 20, 20));
        lineChoiceBtn.setMinSize(TOGGLE_BTN_SIZE, TOGGLE_BTN_SIZE);
        lineChoiceBtn.setToggleGroup(buttonToggleGroup);

        toolbar.getChildren().addAll(circleChoiceBtn, rectangleChoiceBtn, triangleChoiceBtn, lineChoiceBtn);


        ColorPicker colorDropdown = new ColorPicker();
        toolbar.getChildren().add(colorDropdown);

        fillCbx = new CheckBox();
        Label fillCbxLabel = new Label("Fill");

        toolbar.getChildren().addAll(fillCbx, fillCbxLabel);


        Label thicknessLabel = new Label("Thickness");
        thicknessEntryField = new TextField("1");
        thicknessEntryField.setPrefWidth(30);

        thicknessSlider = new Slider();
        thicknessSlider.setMin(1);
        thicknessSlider.setMax(10);
        thicknessSlider.setValue(Integer.parseInt(thicknessEntryField.getText()));
        thicknessSlider.setShowTickLabels(true);

        thicknessSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                thicknessEntryField.setText(String.valueOf(newValue.intValue()));
            }
        });

        thicknessEntryField.textProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                if(Integer.parseInt(newValue) < 1) {
                    newValue = "1";
                } else if (Integer.parseInt(newValue) > 10) {
                    newValue = "10";
                }
                thicknessSlider.setValue(Integer.parseInt(newValue));
            } catch (NumberFormatException exc) {
                // Do nothing because as soon as the field is deleted it registers as Not a Number and
                // it will throw a NumberFormatException
            }
        }));

        toolbar.getChildren().addAll(thicknessLabel, thicknessEntryField, thicknessSlider);







        canvas = new Canvas(CANVAS_INIT_WIDTH, CANVAS_INIT_HEIGHT);
        canvas.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: green");
        canvas.setOnMouseClicked((event) -> {

            double mouseX = event.getX();
            double mouseY = event.getY();

            //Set the Thickness TextField if it does not have a valid value
            thicknessEntryField.setText(String.valueOf((int)thicknessSlider.getValue()));

            IShape shape = null;
            if(circleChoiceBtn.isSelected()) {
                shape = new CircleAdapter(
                        new shapes.Circle(
                                25,
                                mouseX,
                                mouseY,
                                thicknessSlider.getValue(),
                                colorDropdown.getValue()
                        )
                );
            } else if (rectangleChoiceBtn.isSelected()) {
                shape = new RectangleAdapter(
                        new shapes.Rectangle(
                                mouseX,
                                mouseY,
                                25,
                                25,
                                thicknessSlider.getValue(),
                                colorDropdown.getValue()
                        )
                );
            } else if (triangleChoiceBtn.isSelected()) {
                shape = new TriangleAdapter(
                        new Triangle(
                                mouseX,
                                mouseY,
                                25,
                                25,
                                thicknessSlider.getValue(),
                                colorDropdown.getValue()
                        )
                );
            } else if (lineChoiceBtn.isSelected()) {
                int randomXEnd = random.nextInt((int)CANVAS_INIT_WIDTH -1);
                int randomYEnd = random.nextInt((int)CANVAS_INIT_HEIGHT -1);

                shape = new LineAdapter(
                        new shapes.Line(
                                mouseX,
                                mouseY,
                                20,
                                20,
                                thicknessSlider.getValue(),
                                colorDropdown.getValue()
                        ),
                        randomXEnd,
                        randomYEnd
                );
            }

            if(shape != null) addAndDrawShape(shape);
        });

        window.getChildren().addAll(toolbar, canvas);

        return new Scene(window, primaryStage.getWidth(), primaryStage.getHeight());
    }

    private void addAndDrawShape(IShape shape) {
        shape.setFilled(fillCbx.isSelected());
        if(!savedShapes.add(shape)) {
            savedShapes.update(shape, shape.getThickness(), shape.getColor(), shape.getFilled());
        }
        savedShapes.drawShapes(canvas.getGraphicsContext2D());
    }
}
