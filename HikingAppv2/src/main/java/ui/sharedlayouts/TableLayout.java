package ui.sharedlayouts;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import model.Hike;
import ui.UI;
import ui.layouts.CreateHikeScene;
import ui.layouts.HomeScene;

public class TableLayout
{
    UI ui;
    
    public TableLayout(UI ui) {
        this.ui = ui;
    }
    /**
     * Get the base table view
     * @return TableView with all headers added.
     */
    public TableView getTableView()
    {
        TableView table = new TableView();
        table.setEditable(true);
        table.setPrefHeight(300);
        table.setMaxHeight(500);
        table.getStyleClass().add("table-row-cell");

        TableColumn<Hike, String> locationCol = new TableColumn<>("Location");
        locationCol.setPrefWidth(150);
        TableColumn<Hike, String> dateCol = new TableColumn<>("Date");
        dateCol.setPrefWidth(150);
        TableColumn<Hike, String> stepsCol = new TableColumn<>("Steps");
        stepsCol.setPrefWidth(100);
        TableColumn<Hike, String> heartrateCol = new TableColumn("Avg Heart Rate");
        heartrateCol.setPrefWidth(150);
        TableColumn optionsCol = new TableColumn("Options");
        optionsCol.setPrefWidth(200);

        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("datePlanned"));
        stepsCol.setCellValueFactory(new PropertyValueFactory<>("stepsTaken"));
        heartrateCol.setCellValueFactory(new PropertyValueFactory<>("heartRateAverage"));
        
        table.getColumns().addAll(
                locationCol,
                dateCol,
                stepsCol,
                heartrateCol,
                optionsCol);

        TableColumn viewCol = new TableColumn("View Hike");
        TableColumn fitbitCol = new TableColumn("FitBit Data");

        optionsCol.getColumns().addAll(viewCol, fitbitCol);
        return table;
    }

    /**
     * Get bottom box for below table to show home button and create new hike button
     * @return HBox - Containing the buttons for below the table
     */
    public HBox TableBottomBox()
    {
        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        bottomBox.setSpacing(400);

        // add button to add to bottom box
        Button homeButton = new Button();
        homeButton.setPrefWidth(200);
        homeButton.setPrefHeight(50);
        homeButton.setText("Home");
        
        homeButton.setOnMouseClicked((event -> {
            ui.goToScene(new HomeScene(ui));
        }));

        Button addNewButton = new Button();
        addNewButton.setPrefWidth(200);
        addNewButton.setPrefHeight(50);
        addNewButton.setText("Add New Hike");
    
        addNewButton.setOnMouseClicked((event -> {
            ui.goToScene(new CreateHikeScene(ui));
        }));
        
        bottomBox.getChildren().addAll(addNewButton, homeButton);
        
        return bottomBox;
    }
}
