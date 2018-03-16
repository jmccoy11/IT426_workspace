package buttons;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public abstract class ButtonExtension extends Button{
    
    public ButtonExtension(String label) {
        super(label);
        
        setOnAction((event) -> {
            showPopup(event);
            changeColor();
            changeText();
            resizeButton();
        });
    }
    
    public abstract void showPopup(ActionEvent event);
    
    public void changeColor(){
    
    }
    
    public void changeText() {
        System.out.println(this + "is the button class that called changeText");
        System.out.println("This is  default behaviour but doesn't do anything.");
    }
    
    public void resizeButton() {
    
    }
}
