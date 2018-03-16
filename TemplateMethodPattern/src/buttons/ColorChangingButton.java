package buttons;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ui.UI;

import java.util.Random;

public class ColorChangingButton extends ButtonExtension {
    private Random random = new Random();

    public ColorChangingButton(String label) {
        super(label);
    }
    
    @Override
    public void showPopup(ActionEvent event) {
        Label colorChangePopup = new Label("Changing color!");
        colorChangePopup.setAlignment(Pos.CENTER);
        colorChangePopup.setTextFill(getRandomColor());
        colorChangePopup.setStyle("-fx-font-size: 20;" +
                "-fx-font-weight: bold");

        ScrollPane popoverScrollPane = (ScrollPane) UI.getPopoverPane();
        VBox popOverBox = (VBox) popoverScrollPane.getContent();
        popOverBox.getChildren().add(colorChangePopup);
    }
    
    public void changeColor() {
        setStyle("-fx-font-weight: bold");
        setFillAndTextColor(getRandomColor(), getRandomColor());
    }
    
    private void setFillAndTextColor(Color background, Color textColor) {
        setBackground(new Background(new BackgroundFill(background, CornerRadii.EMPTY, Insets.EMPTY)));
        setTextFill(textColor);
    }

    private Color getRandomColor() {
        return Color.rgb(random.nextInt(255),
                random.nextInt(255),
                random.nextInt(255));
    }
}
