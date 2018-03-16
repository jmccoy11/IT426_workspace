package buttons;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ColorChangingButton extends ButtonExtension {
    
    public ColorChangingButton(String label) {
        super(label);
    }
    
    @Override
    public void showPopup() {
        System.out.println("You clicked a color changing button.");
    }
    
    public void changeColor() {
        if(getBackground().getFills().get(0).getFill().equals(Color.BLACK)) {
            setFillAndTextColor(Color.WHITE, Color.BLACK);
        } else {
            setFillAndTextColor(Color.BLACK, Color.WHITE);
        }
    }
    
    private void setFillAndTextColor(Color background, Color textColor) {
        setBackground(new Background(new BackgroundFill(background, CornerRadii.EMPTY, Insets.EMPTY)));
        setTextFill(textColor);
    }
}
