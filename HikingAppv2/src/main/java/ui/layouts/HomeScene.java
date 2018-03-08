package ui.layouts;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ui.UI;

import java.io.File;
import java.net.MalformedURLException;
import java.util.*;

public class HomeScene extends WindowScene {
    
    private static final int IMAGE_FIT_WIDTH = 200;
    private static final int IMAGE_FIT_HEIGHT = 300;
    private final double BTN_WIDTH = 0.33;
    
    private ArrayList<Node> buttons;
    
    public HomeScene(UI ui) {
        super(ui);
        buttons = new ArrayList<>();
    
        setLayout(createAppLayout());
        getLayout().getStylesheets().add("styles/main.css");
        getLayout().getStylesheets().add("styles/home.css");
    }
    
    private Scene createAppLayout() {
        HBox layout = new HBox();
        layout.getStyleClass().add("main");

        layout.getChildren().addAll(getVerticalButtons());
        
        return new Scene(layout);
    }
    
    private Collection<Node> getVerticalButtons() {
        Node[] buttons = {
                getViewHikesVBox(),
                getNewHikeVBox(),
                getPlannedHikeRecordsVBox()
        };
        
        Collection<Node> collection = new LinkedList<>();
        collection.addAll(Arrays.asList(buttons));
        
        return collection;
    }
    
    private VBox getViewHikesVBox()  {
        VBox viewHikesButton = new VBox();
        viewHikesButton.getStyleClass().add("main-view-btn");
        viewHikesButton.getStyleClass().add("view-hikes-btn");
        viewHikesButton.prefWidthProperty()
                .bind(getUi().getPrimaryStage().widthProperty().multiply(BTN_WIDTH));

        displaySelection(viewHikesButton, "viewhikeslogo.png", "View All Hikes Created");

        viewHikesButton.setOnMouseClicked((event) -> {
            getUi().goToScene(new ViewHikesScene(getUi()));
        });
        
        return viewHikesButton;
    }
    
    private VBox getNewHikeVBox() {
        VBox newHikeButton = new VBox();
        newHikeButton.getStyleClass().add("main-view-btn");
        newHikeButton.getStyleClass().add("new-hike-btn");
        newHikeButton.prefWidthProperty()
                .bind(getUi().getPrimaryStage().widthProperty().multiply(BTN_WIDTH));

        displaySelection(newHikeButton, "hiker.png", "Create New Hike");

        newHikeButton.setOnMouseClicked((event) -> {
            getUi().goToScene(new CreateHikeScene(getUi()));
        });
        
        return newHikeButton;
    }

    private VBox getPlannedHikeRecordsVBox() {
        VBox plannedHikesButton = new VBox();
        plannedHikesButton.getStyleClass().add("main-view-btn");
        plannedHikesButton.getStyleClass().add("planned-hikes-btn");
        plannedHikesButton.prefWidthProperty()
                .bind(getUi().getPrimaryStage().widthProperty().multiply(BTN_WIDTH));

        displaySelection(plannedHikesButton, "calendar.png", "View Planned Hikes");

        plannedHikesButton.setOnMouseClicked((event) -> {
            getUi().goToScene(new PlannedHikesScene(getUi()));
        });
        
        return plannedHikesButton;
    }
    
    public List<Node> getButtons() {
        return buttons;
    }

    private void displaySelection(VBox tempBox, String imageName, String labelText)
    {
        try
        {
            Image image = new Image(new File("images/" + imageName)
                    .toURI().toURL().toString());
            ImageView imageControl = new ImageView(image);

            imageControl.setFitWidth(IMAGE_FIT_WIDTH);
            imageControl.setFitHeight(IMAGE_FIT_HEIGHT);

            tempBox.getChildren().add(imageControl);

            Label lastHikes = new Label(labelText);
            lastHikes.getStyleClass().add("img-label");

            tempBox.getChildren().add(lastHikes);
        }
        catch(MalformedURLException ex)
        {
            Label clickMe = new Label(labelText);
            clickMe.setAlignment(Pos.CENTER);
            clickMe.setStyle("-fx-font-size: 50px");
            clickMe.setTextFill(Color.BLACK);

            tempBox.getChildren().add(clickMe);
        }
    }
}
