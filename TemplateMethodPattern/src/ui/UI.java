package ui;

import buttons.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UI extends Application {
    private static HBox window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Template Method Pattern");

        window = new HBox();
        window.setAlignment(Pos.CENTER);

        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        box.setPadding(new Insets(10));
        box.setPrefWidth(400);
        
        ButtonExtension defaultBtn = new DefaultButton("Default");
        ButtonExtension colorChangeBtn = new ColorChangingButton("Color Changing");
        ButtonExtension sizeChangeBtn = new SizeChangingButton("Size Changing");
        ButtonExtension textChangeBtn = new TextChangingButton("Text Changer");
    
        box.getChildren().addAll(defaultBtn, colorChangeBtn, sizeChangeBtn, textChangeBtn);

        VBox popoverPane = new VBox();
        popoverPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        popoverPane.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefWidth(500);
        scrollPane.setFitToWidth(true);
        scrollPane.setUserData("popover");
        scrollPane.setContent(popoverPane);

        popoverPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue((Double) newValue);
        });

        window.getChildren().addAll(box, scrollPane);

        Scene newScene = new Scene(window, 800, 300);
        primaryStage.setScene(newScene);
        primaryStage.show();
    }

    public static Node getPopoverPane() {
        for(Node node : window.getChildren()) {
            Object nodeData = node.getUserData();
            if (nodeData != null) {
                if(((String)nodeData).equals("popover")) {
                    return node;
                }
            }
        }
        return null;
    }
}
