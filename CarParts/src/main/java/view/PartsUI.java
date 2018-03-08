package view;

import controller.PartsController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CarPart;

import java.io.File;
import java.util.Collection;

public class PartsUI extends Application
{
    private static final int RIGHT_PERCENT = 60;
    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 400;
    private static final int STANDARD_GAP = 10;
    private static final int HALF_WIDTH = 50;
    private static final int LEFT_PERCENT = 40;

    //model classes
    private PartsController controller;

    //entry fields
    private TextField partId;
    private TextField manufacturer;
    private TextField listPrice;

    private ToggleGroup exportToggle;
    private ToggleGroup importToggle;

    //list of parts
    private ListView partsList;

    public PartsUI()
    {
        controller = new PartsController();
    }

    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Parts Database");
        primaryStage.setScene(getScene());
        primaryStage.show();
    }

    private Scene getScene() throws Exception
    {
        //three primary parts of UI
        GridPane left = getLeftPane();
        VBox right = getRightPane();
        VBox bottom = getBottomPane();

        //set up the parent panel
        GridPane parent = new GridPane();
        parent.setHgap(STANDARD_GAP);
        parent.setVgap(STANDARD_GAP);
        parent.setPadding(new Insets(0, STANDARD_GAP, STANDARD_GAP, STANDARD_GAP));

        //row/column considerations
        ColumnConstraints columnLeft = new ColumnConstraints();
        ColumnConstraints columnRight = new ColumnConstraints();
        columnLeft.setPercentWidth(HALF_WIDTH);
        columnRight.setPercentWidth(HALF_WIDTH);

        parent.getColumnConstraints().add(columnLeft);
        parent.getColumnConstraints().add(columnRight);

        parent.add(left, 0, 1);
        parent.add(right, 1, 1);
        parent.add(bottom, 0, 2, 2, 1);

        Scene scene = new Scene(parent, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(new File("src/styles/styles.css").
                                   toURI().toURL().toExternalForm());

        return scene;
    }

    private GridPane getLeftPane()
    {
        GridPane left = new GridPane();
        left.setVgap(4);
        left.setHgap(4);
        left.setPadding(new Insets(4, 4, 4,4));

        ColumnConstraints columnLeft = new ColumnConstraints();
        ColumnConstraints columnRight = new ColumnConstraints();
        columnLeft.setPercentWidth(LEFT_PERCENT);
        columnRight.setPercentWidth(RIGHT_PERCENT);

        left.getColumnConstraints().addAll(columnLeft, columnRight);

        partId = addTextControl("Part Id #", "partId",
                                0, left);
        manufacturer = addTextControl("Manufacturer", "manufacturer",
                                      1, left);
        listPrice = addTextControl("List Price", "listPrice",
                                   2, left);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getStyleClass().add("border-top");

        buildAddPartButton(hbox);

        left.add(hbox, 0, 5, 2, 1);
        left.getStyleClass().add("panel");
        return left;
    }

    private void buildAddPartButton(HBox parent)
    {
        Button add = new Button("Add Part");
        add.setPadding(new Insets(4,30,4,30));
        parent.getChildren().add(add);

        add.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                addPart();

                //clear text field values
                partId.setText("");
                manufacturer.setText("");
                listPrice.setText("");
            }
        });
    }

    private TextField addTextControl(String labelText, String controlName,
                                     int row, GridPane parent)
    {
        Label label = new Label(labelText);
        TextField textField = new TextField();

        textField.getStyleClass().add("text");

        parent.add(label, 0, row);
        parent.add(textField, 1, row);

        return textField;
    }

    private VBox getRightPane()
    {
        //the panel with import/export parts
        VBox parentPanel = new VBox();
        parentPanel.getStyleClass().add("panel");

        //intermediate objects needed to put together our panels
        String[] options = {"Java", "JSON", "XML"};
        importToggle = new ToggleGroup();
        exportToggle = new ToggleGroup();

        //import/export sections
        buildImportExport(parentPanel, options, "Import", importToggle);
        buildImportExport(parentPanel, options, "Export", exportToggle);

        return parentPanel;
    }

    private void buildImportExport(VBox parentPanel, String[] options,
                                   String displayMessage, ToggleGroup toggleGroup)
    {
        HBox panel = new HBox();
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(6,0,0,0));

        Button button = new Button(displayMessage);
        panel.getChildren().add(button);

        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (displayMessage.equals("Import"))
                {
                    importParts();
                }
                else
                {
                    exportParts();
                }
            }
        });

        HBox choices = getRadioButtons(toggleGroup, options);
        parentPanel.getChildren().addAll(panel, choices);
    }

    private Button getButton(String text)
    {
        Button button = new Button(text);
        button.getStyleClass().add("button");
        return button;
    }

    private HBox getRadioButtons(ToggleGroup group, String[] options)
    {
        HBox hbox = new HBox();
        hbox.setMaxWidth(Double.MAX_VALUE);
        hbox.setMaxWidth(Double.MAX_VALUE);
        hbox.setAlignment(Pos.BOTTOM_CENTER);

        for (int i = 0; i < options.length; i++)
        {
            RadioButton rButton = new RadioButton();
            rButton.setText(options[i]);
            rButton.setPadding(new Insets(STANDARD_GAP));
            rButton.setToggleGroup(group);

            if (i == 0)
            {
                rButton.setSelected(true);
            }

            hbox.getChildren().add(rButton);
        }

        return hbox;
    }

    private VBox getBottomPane()
    {
        VBox panel = new VBox();
        partsList = new ListView();

        panel.getChildren().add(partsList);
        panel.getStyleClass().add("panel");

        return panel;
    }

    private void addPart()
    {
        controller.addPart(partId.getText(), manufacturer.getText(),
                Double.parseDouble(listPrice.getText()));
        updateUI();
    }

    private void exportParts()
    {
        RadioButton rButton = (RadioButton) exportToggle.getSelectedToggle();
        String choice = rButton.getText();
        controller.exportParts(choice);
    }

    private void importParts()
    {
        RadioButton rButton = (RadioButton) importToggle.getSelectedToggle();
        String choice = rButton.getText();
        controller.importParts(choice);

        updateUI();
    }

    private void updateUI()
    {
        Collection<CarPart> parts = controller.getParts();
        ObservableList<CarPart> partsItems = FXCollections.observableArrayList(parts);
        partsList.setItems(partsItems);
    }
}
