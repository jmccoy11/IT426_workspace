package hello_world;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloWorldApp extends Application {

    private static final int WIN_WIDTH = 500;
    private static final int WIN_HEIGHT = 500;
    private static final int PADDING = 10;
    private static final int APP_FONT_SIZE = 30;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World App!");
        stage.setScene(getScene());
        stage.show();
    }

    //this will assemble our UI
    private Scene getScene() {

        //layout to hold controls
        VBox box = new VBox();

        //centers the child elements of the box
        box.setAlignment(Pos.CENTER);

        //add padding around the child elements
        box.setPadding(new Insets(PADDING));

        //controls
        Text text = new Text("Hello World!");

        //shows all the fonts downloaded in JavaFX
        System.out.println(javafx.scene.text.Font.getFamilies());
        
        text.setFont(Font.font("Matura MT Script Capitals", FontWeight.BOLD, APP_FONT_SIZE));

        //add control to the layout, the layout to the scene
        box.getChildren().add(text);
        return new Scene(box, WIN_WIDTH, WIN_HEIGHT);
    }
}
