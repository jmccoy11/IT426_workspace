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
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import shapes.Triangle;

import java.util.Random;

public class DoodlePadUI extends Application{

    public static final double CANVAS_INIT_WIDTH = 770;
    public static final double CANVAS_INIT_HEIGHT = 490;

    private final double WINDOW_MIN_WIDTH = 800;
    private final double WINDOW_MIN_HEIGHT = 600;
    private final double WINDOW_MAX_WIDTH = 800;
    private final double WINDOW_MAX_HEIGHT = 600;

    private final int TOGGLE_BTN_SIZE = 30;

    private Stage primaryStage;
    private Canvas canvas;
    private SavedShapes savedShapes;
    private Random random = new Random();

    //pollable fields
    private ToggleGroup buttonToggleGroup;
    private ColorPicker colorDropdown;
    private CheckBox fillCbx;
    private TextField thicknessEntryField;
    private Slider thicknessSlider;

    public DoodlePadUI() {
        savedShapes = new SavedShapes();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        setWindowProperties();
        savedShapes.drawShapes(canvas.getGraphicsContext2D());
        primaryStage.show();
    }

    private void setWindowProperties() {
        primaryStage.setMinWidth(WINDOW_MIN_WIDTH);
        primaryStage.setMinHeight(WINDOW_MIN_HEIGHT);
        primaryStage.setMaxWidth(WINDOW_MAX_WIDTH);
        primaryStage.setMaxHeight(WINDOW_MAX_HEIGHT);
        primaryStage.setTitle("DoodlePad");
        primaryStage.setScene(getScene());
    }

    private VBox buildWindowScene() {
        VBox window = new VBox();

        window.getStyleClass().add("window");
        window.prefWidthProperty().bind(primaryStage.widthProperty());
        window.prefHeightProperty().bind(primaryStage.heightProperty());

        return window;
    }

    private void buildToggleButtons(HBox toolbar) {
        buttonToggleGroup = new ToggleGroup();

        toolbar.getChildren().addAll(createCircleToggleBtn(),
                createRectToggleBtn(),
                createTriangleToggleBtn(),
                createLineToggleButton());
    }

    private ToggleButton createCircleToggleBtn() {
        Circle circleChoiceIcon = new Circle(10, Color.TEAL);
        circleChoiceIcon.setStroke(Color.BLACK);
        ToggleButton circleChoiceBtn = new ToggleButton("", circleChoiceIcon);
        circleChoiceBtn.setMinSize(TOGGLE_BTN_SIZE, TOGGLE_BTN_SIZE);
        circleChoiceBtn.setToggleGroup(buttonToggleGroup);
        circleChoiceBtn.setUserData("circle");
        buttonToggleGroup.selectToggle(circleChoiceBtn);
        return circleChoiceBtn;
    }

    private ToggleButton createRectToggleBtn() {
        Rectangle rectangleChoiceIcon = new Rectangle(20, 15, Color.YELLOW);
        rectangleChoiceIcon.setStroke(Color.BLACK);
        ToggleButton rectangleChoiceBtn = new ToggleButton("", rectangleChoiceIcon);
        rectangleChoiceBtn.setMinSize(TOGGLE_BTN_SIZE, TOGGLE_BTN_SIZE);
        rectangleChoiceBtn.setToggleGroup(buttonToggleGroup);
        rectangleChoiceBtn.setUserData("rectangle");

        return rectangleChoiceBtn;
    }

    private ToggleButton createTriangleToggleBtn() {
        Polygon triangleChoiceIcon = new Polygon(
                10.0, 0.0,
                0.0, 15.0,
                20.0, 15.0);
        triangleChoiceIcon.setStroke(Color.BLACK);
        triangleChoiceIcon.setFill(Color.LIME);
        ToggleButton triangleChoiceBtn = new ToggleButton("", triangleChoiceIcon);
        triangleChoiceBtn.setMinSize(TOGGLE_BTN_SIZE, TOGGLE_BTN_SIZE);
        triangleChoiceBtn.setToggleGroup(buttonToggleGroup);
        triangleChoiceBtn.setUserData("triangle");
        return triangleChoiceBtn;
    }

    private ToggleButton createLineToggleButton() {
        ToggleButton lineChoiceBtn = new ToggleButton("", new Line(0, 0, 20, 20));
        lineChoiceBtn.setMinSize(TOGGLE_BTN_SIZE, TOGGLE_BTN_SIZE);
        lineChoiceBtn.setToggleGroup(buttonToggleGroup);
        lineChoiceBtn.setUserData("line");
        return lineChoiceBtn;
    }

    public void createThicknessTool() {
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
    }

    private Button createClearBtn() {
        Button clear = new Button("Clear");
        clear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                savedShapes = new SavedShapes();
                savedShapes.drawShapes(canvas.getGraphicsContext2D());
            }
        });

        return clear;
    }
    private HBox buildToolbar() {
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("toolbar");

        buildToggleButtons(toolbar);

        colorDropdown = new ColorPicker();

        fillCbx = new CheckBox();
        Label fillCbxLabel = new Label("Fill");

        Label thicknessLabel = new Label("Thickness");
        createThicknessTool();

        Button clear = createClearBtn();

        toolbar.getChildren().addAll(colorDropdown,
                fillCbx,
                fillCbxLabel,
                thicknessLabel,
                thicknessEntryField,
                thicknessSlider,
                clear);

        return toolbar;
    }

    private Scene getScene() {
        VBox window = buildWindowScene();

        HBox toolbar = buildToolbar();

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

            System.out.println(buttonToggleGroup.getSelectedToggle().getUserData());
            String selectedToggle = (String) buttonToggleGroup.getSelectedToggle().getUserData();
            if(selectedToggle.equals("circle")) {
                shape = new CircleAdapter(
                        new shapes.Circle(
                                25,
                                mouseX,
                                mouseY,
                                thicknessSlider.getValue(),
                                colorDropdown.getValue()
                        )
                );
            } else if (selectedToggle.equals("rectangle")) {
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
            } else if (selectedToggle.equals("triangle")) {
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
            } else if (selectedToggle.equals("line")) {
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

        Scene mainScene = new Scene(window, primaryStage.getWidth(), primaryStage.getHeight());
        mainScene.getStylesheets().add("main.css");
        return mainScene;
    }

    private void addAndDrawShape(IShape shape) {
        shape.setFilled(fillCbx.isSelected());
        if(!savedShapes.add(shape)) {
            savedShapes.update(shape, shape.getThickness(), shape.getColor(), shape.getFilled());
        }
        savedShapes.drawShapes(canvas.getGraphicsContext2D());
    }
}
