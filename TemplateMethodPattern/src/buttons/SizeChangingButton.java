package buttons;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import ui.UI;

public class SizeChangingButton extends ButtonExtension {
    
    public SizeChangingButton(String label) {
        super(label);
    }
    
    @Override
    public void showPopup(ActionEvent event) {
        String text;
        if (getPrefWidth() == 400) {
            text = "400x100";
        } else {
            text = "100x50";
        }

        Label sizeChangePopup = new Label("Button size changing to " + text);
        sizeChangePopup.setAlignment(Pos.CENTER);
        sizeChangePopup.setStyle("-fx-font-size: 20;" +
                "-fx-text-fill: red;" +
                "-fx-font-weight: bold");

        ScrollPane popoverScrollPane = (ScrollPane)UI.getPopoverPane();
        VBox popOverBox = (VBox) popoverScrollPane.getContent();
        popOverBox.getChildren().add(sizeChangePopup);
    }
    
    public void resizeButton() {
        if(getPrefWidth() == 400) {
            setPrefSize(100, 50);
        } else {
            setPrefSize(400, 100);
        }
    }
}
