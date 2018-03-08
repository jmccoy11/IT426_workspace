package ui.layouts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.Hike;
import ui.UI;
import ui.sharedlayouts.PageHeader;

import java.util.List;

public class ViewHikesScene extends WindowScene {
    
    public ViewHikesScene(UI ui) {
        super(ui);

        // add our root layout
        BorderPane root = new BorderPane();
        root.getStyleClass().add("main");
        root.getStyleClass().add("tableLayout");
        root.setStyle("-fx-background-color: #DCDCDC");

        TableView table = getUi().getTableLayout().getTableView();
    
        List<Hike> hikes = getUi().getController().getHikes();
        ObservableList<Hike> hikesList = FXCollections.observableArrayList(hikes);
        table.setItems(hikesList);
        
        HBox bottomBox = getUi().getTableLayout().TableBottomBox();

        // add homebutton to controller to go home
        //homeButton.setOnAction(Controller.goHome());

        root.setTop(PageHeader.LabelHeader("View All Hikes"));
        root.setCenter(table);
        root.setBottom(bottomBox);

        setLayout(new Scene(root));
        getLayout().getStylesheets().add("styles/main.css");
//        getLayout().getStylesheets().add("styles/tableLayout.css");
    }


    private Scene createAppLayout() {
        return null;
    }
}
