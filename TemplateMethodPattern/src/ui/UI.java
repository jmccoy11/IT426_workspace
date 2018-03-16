package ui;

import buttons.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UI extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Template Method Pattern");
        
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        box.setPadding(new Insets(10));
        
        ButtonExtension defaultBtn = new DefaultButton("Default");
        ButtonExtension colorChangeBtn = new ColorChangingButton("Color Changing");
        ButtonExtension sizeChangeBtn = new SizeChangingButton("Size Changing");
        ButtonExtension textChangeBtn = new TextChangingButton("Text Changing");
    
        box.getChildren().addAll(defaultBtn, colorChangeBtn, sizeChangeBtn, textChangeBtn);
        
        Scene newScene = new Scene(box, 500, 500);
        primaryStage.setScene(newScene);
        primaryStage.show();
    }
}
