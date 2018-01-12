package scenes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StoryBook extends Application{
    
    private static final int DELAY = 3000;
    private static final int PADDING = 10;
    private static final int SPACING = 10;
    private static final int FONT_SIZE = 30;
    private static final Font APP_FONT_STYLE = Font.font("Matura MT Script Capitals", FontWeight.BOLD, FONT_SIZE);
    private static final int APP_WIDTH = 500;
    private static final int APP_HEIGHT = 500;
    private static final int BTN_HEIGHT = 30;
    private static final int BTN_WIDTH = 300;
    //save our stage
    private Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        
        //prepare the window
        stage.setTitle("Working with scenes!");
        stage.setScene(getWaitingScene());
        stage.show();
        
        //we are going to wait on the current screen using
        //animation (key frame)
        KeyFrame frame = new KeyFrame(Duration.millis(DELAY), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(getStorySceneOne());
            }
        });
    
        Timeline waitAnimation = new Timeline(frame);
        waitAnimation.play();
    }
    
    private Scene getWaitingScene() {
        //create a layout
        VBox waitingLayout = new VBox();
        
        //center child elements of the layout
        waitingLayout.setAlignment(Pos.CENTER);
        
        //spacebetween child elements and edge of layout
        waitingLayout.setPadding(new Insets(PADDING));
        
        //space between child elements;
        waitingLayout.setSpacing(SPACING);
        
        //add a few elements
        ProgressIndicator progressIndicator = new ProgressIndicator();
        Text waitingMessage = new Text("Please wait...");
        waitingMessage.setFont(APP_FONT_STYLE);
        
        waitingLayout.getChildren().add(progressIndicator);
        waitingLayout.getChildren().add(waitingMessage);
        
        //generate scene based on layout
        return new Scene(waitingLayout, APP_WIDTH, APP_HEIGHT);
    }
    
    private Scene getStorySceneOne() {
        VBox sceneOneLayout = new VBox();
    
        sceneOneLayout.setAlignment(Pos.CENTER);
    
        sceneOneLayout.setPadding(new Insets(PADDING));
    
        Text sceneOneText = new Text("Smaug was eating some mutton...");
        sceneOneText.setFont(APP_FONT_STYLE);
    
        Button btnContinue = new Button("Continue");
        btnContinue.setMaxHeight(BTN_HEIGHT);
        btnContinue.setMaxWidth(BTN_WIDTH);
        
        btnContinue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(getStorySceneTwo());
            }
        });
        
        sceneOneLayout.getChildren().add(sceneOneText);
        sceneOneLayout.getChildren().add(btnContinue);
        
        return new Scene(sceneOneLayout, APP_WIDTH, APP_HEIGHT);
    }
    
    private Scene getStorySceneTwo() {
        VBox sceneTwoLayout = new VBox();
    
        sceneTwoLayout.setAlignment(Pos.CENTER);
    
        sceneTwoLayout.setPadding(new Insets(PADDING));
    
        Text sceneTwoText = new Text("When suddenly he heard someone muttering about some \"Precious...\"");
        sceneTwoText.setFont(APP_FONT_STYLE);
        sceneTwoText.setWrappingWidth(APP_WIDTH);
        sceneTwoText.setTextAlignment(TextAlignment.CENTER);
    
        Button btnContinue = new Button("Continue");
        btnContinue.setMaxHeight(BTN_HEIGHT);
        btnContinue.setMaxWidth(BTN_WIDTH);
    
        btnContinue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(getStorySceneTwo());
            }
        });
    
        sceneTwoLayout.getChildren().add(sceneTwoText);
        sceneTwoLayout.getChildren().add(btnContinue);
    
        return new Scene(sceneTwoLayout, APP_WIDTH, APP_HEIGHT);
    }
}
