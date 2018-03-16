package buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public abstract class ButtonExtension extends Button{
    
    public ButtonExtension(String label) {
        super(label);
        
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showPopup();
                changeColor();
                changeText();
                resizeButton();
            }
        });
    }
    
    public abstract void showPopup();
    
    public void changeColor(){
    
    }
    
    public void changeText() {
        System.out.println("I'm the default behavior but I don't do anything.");
    }
    
    public void resizeButton() {
    
    }
}
