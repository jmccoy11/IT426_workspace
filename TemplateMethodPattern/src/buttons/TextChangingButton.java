package buttons;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import ui.UI;

public class TextChangingButton extends ButtonExtension {
    
    private String oldText;
    private String defaultText = "Default";
    
    public TextChangingButton(String label) {
        super(label);
    }
    
    @Override
    public void showPopup(ActionEvent event) {
        String text;
        if(oldText == null) {
            text = defaultText;
        } else {
            text = oldText;
        }

        Label textChangePopup = new Label("Changing text to " + text);
        textChangePopup.setAlignment(Pos.CENTER);
        textChangePopup.setStyle("-fx-font-size: 20;" +
                "-fx-text-fill: blue;" +
                "-fx-font-weight: bold");

        ScrollPane popoverScrollPane = (ScrollPane) UI.getPopoverPane();
        VBox popOverBox = (VBox) popoverScrollPane.getContent();
        popOverBox.getChildren().add(textChangePopup);
    }
    
    public void changeText() {
        if(oldText == null) {
            oldText = getText();
            setText(defaultText);
        } else {
            setText(oldText);
            oldText = null;
        }
    }
}
