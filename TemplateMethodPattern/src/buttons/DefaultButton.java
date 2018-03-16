package buttons;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import ui.UI;

public class DefaultButton extends ButtonExtension {
    
    public DefaultButton(String label) {
        super(label);
    }
    
    @Override
    public void showPopup(ActionEvent event) {
        Label defaultChange = new Label("You clicked a default button.");
        defaultChange.setAlignment(Pos.CENTER);
        defaultChange.setStyle("-fx-font-size: 20;" +
                "-fx-text-fill: black;" +
                "-fx-font-weight: bold");

        ScrollPane popoverScrollPane = (ScrollPane) UI.getPopoverPane();
        VBox popOverBox = (VBox) popoverScrollPane.getContent();
        popOverBox.getChildren().add(defaultChange);
    }
}
