package ui.sharedlayouts;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PageHeader
{
    /**
     * Create a Label header for a view
     * @param label String - Text to display
     * @return HBox - Containing the header text to display
     */
    public static HBox LabelHeader(String label)
    {
        HBox header = new HBox();
        header.setStyle("-fx-background-color: #DCDCDC");
        header.setAlignment(Pos.TOP_CENTER);
        header.setPrefHeight(40);

        Label headerText = new Label(label);
        headerText.setAlignment(Pos.CENTER);
        headerText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));

        header.getChildren().add(headerText);

        return header;
    }
}
