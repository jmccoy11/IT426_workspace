package ui;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import ui.layouts.HomeScene;
import ui.layouts.WindowScene;
import ui.sharedlayouts.PageHeader;
import ui.sharedlayouts.TableLayout;

public class UI extends Application {
    private Controller controller;
    
    private Stage primaryStage;
    private HomeScene home;
    
    private TableLayout tableLayout;
    
    private final int APP_MIN_HEIGHT = 300;
    private final int APP_MIN_WIDTH = 400;
    
    public UI() {
        this.controller = new Controller();
    }
    
    @Override
    public void start(Stage primaryStage) {
        tableLayout = new TableLayout(this);
        
        this.primaryStage = primaryStage;
        this.home = new HomeScene(this);
        
        primaryStage.setTitle("Let's Hike!");
        primaryStage.setScene(home.getLayout());
        primaryStage.setMinHeight(APP_MIN_HEIGHT);
        primaryStage.setMinWidth(APP_MIN_WIDTH);
        
        primaryStage.show();
    }
    
    public Controller getController() {
        return controller;
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public TableLayout getTableLayout(){
        return tableLayout;
    }
    
    public void goToScene(WindowScene scene) {
        primaryStage.setScene(scene.getLayout());
        primaryStage.show();
    }
}
