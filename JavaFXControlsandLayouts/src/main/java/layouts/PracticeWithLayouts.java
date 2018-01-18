package layouts;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Stack;

public class PracticeWithLayouts extends Application{
    
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Practicing with Layouts");
        primaryStage.setScene(useBorderPane());
        primaryStage.show();
    }
    
    //GridPane - rows & columns
    public Scene useGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10));
        
        //create a three-column layout
        grid.getColumnConstraints().add(new ColumnConstraints(100));
        grid.getColumnConstraints().add(new ColumnConstraints(100));
        grid.getColumnConstraints().add(new ColumnConstraints(100));
        
        //create some controls
        RadioButton button1 = new RadioButton("Option 1");
        RadioButton button2 = new RadioButton("Option 2");
        RadioButton button3 = new RadioButton("Option 3");

//        button1.setMaxWidth(90);
//        button2.setMaxWidth(90);
//        button3.setMaxWidth(90);
        
        ToggleGroup group = new ToggleGroup();
        button1.setToggleGroup(group);
        button2.setToggleGroup(group);
        button3.setToggleGroup(group);
    
        Button spanButton = new Button("Please click here!!!");
        spanButton.setPrefWidth(300);
    
        TextArea area = new TextArea();
        Text banner = new Text("Lots of interesting information located here...");
        banner.setWrappingWidth(80);
        banner.setTextAlignment(TextAlignment.CENTER);
        
        //arrange them
        grid.add(button1, 0, 0);
        grid.add(button2, 1, 0);
        grid.add(button3, 2, 0);
    
        grid.add(spanButton, 1, 1, 3, 1);
    
        grid.add(area, 0, 2, 2, 1);
        grid.add(banner, 1, 2, 1, 1);
        
        return new Scene(grid, 300, 300);
    }
    
    //StackPane - place elements on top of each other
    public Scene useStackPane() {
        StackPane pane = new StackPane();
    
        //create a few controls
        Circle circle1 = new Circle(170);
        Circle circle2 = new Circle(50);
        Circle circle3 = new Circle(120);
        
        circle1.setFill(Color.ANTIQUEWHITE);
        circle2.setFill(Color.ALICEBLUE);
        circle3.setFill(Color.ORANGERED);
        
        //add the controls to the layout
        pane.getChildren().addAll(circle1, circle2, circle3);
        
        //You can place complex controls over other controls
        Button button = new Button("Click me!");
        pane.getChildren().add(button);
        
        //move elements on the stack pane
        StackPane.setAlignment(circle1, Pos.TOP_RIGHT);
        StackPane.setAlignment(circle2, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(circle3, Pos.BOTTOM_RIGHT);
    
        StackPane.setMargin(button, new Insets(0, 100, 150, 0));
        
        return new Scene(pane, 300, 300);
    }
    
    //BorderPane - place elements in regions - north, south, east, west, center
    public Scene useBorderPane() throws MalformedURLException {
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10));
        
        //north
        HBox northPanel = new HBox();
        northPanel.setAlignment(Pos.CENTER);
        northPanel.setPadding(new Insets(10));
        
        northPanel.getChildren().addAll(new Button("Please"),
                new Button("Click"),
                new Button("Here"),
                new Button("Now"),
                new Button("!"));
        
        pane.setTop(northPanel);
        
        //south
        Label lotsOfText = new Label("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
                "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
                "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        lotsOfText.setWrapText(true);
        pane.setBottom(lotsOfText);
        
        //west
        Text header = new Text("Favorite Colors");
        header.setUnderline(true);
    
        //primary Layout
        VBox westPanel = new VBox();
        westPanel.setAlignment(Pos.CENTER);
        westPanel.setPadding(new Insets(10));
        westPanel.setSpacing(10);
    
        String[] choices = {"Blue", "Purple", "Pink", "Orange", "White"};
        CheckBox[] checkBoxes = new CheckBox[choices.length];
    
        for(int i = 0; i < choices.length; i++) {
            CheckBox chkBox = new CheckBox(choices[i]);
            westPanel.setPrefWidth(100);
            westPanel.setAlignment(Pos.CENTER_LEFT);
        
            checkBoxes[i] = chkBox;
            westPanel.getChildren().add(chkBox);
        }
    
        pane.setLeft(westPanel);
    
        //east
        VBox eastPanel = new VBox();
        eastPanel.setAlignment(Pos.CENTER);
        eastPanel.setSpacing(10);
        eastPanel.setPadding(new Insets(10));
        
        //display a list
        ListView view = new ListView();
        ObservableList items = FXCollections.observableArrayList(
                "computer games", "movies", "drinking", "programming", "snowboarding");
        ListView list = new ListView(items);
        list.setPrefWidth(150);
    
        eastPanel.getChildren().addAll(list);
        
        pane.setRight(eastPanel);
        
        //center
        Image image = new Image(new File("images/lovebots-medium.png")
                .toURI().toURL().toString());
        ImageView imageControl = new ImageView(image);
    
        //resize the view control
        imageControl.setFitHeight(200);
        imageControl.setFitWidth(300);
    
        pane.setCenter(imageControl);
        
        return new Scene(pane, 600, 600);
    }
    
    //others - FlowPane, TilePane, AnchorPane
}
