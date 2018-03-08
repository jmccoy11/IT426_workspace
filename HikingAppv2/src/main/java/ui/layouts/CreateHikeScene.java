package ui.layouts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Reminder;
import model.Task;
import ui.UI;
import ui.layouts.HomeScene;
import ui.layouts.WindowScene;
import ui.sharedlayouts.PageHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateHikeScene extends WindowScene {
    
    private HashMap<String, String> sceneInput = new HashMap<>();
    private List<Task> tasks = new ArrayList<>();
    private List<Reminder> reminders = new ArrayList<>();
    
    private TextField locationInput;
    private DatePicker hikeDatePicker;
    
    public CreateHikeScene(UI ui) {
        super(ui);
        
        // add our root layout
        BorderPane root = new BorderPane();
        root.getStyleClass().add("main");
        root.getStyleClass().add("root");
        
        VBox topBox = new VBox();
        topBox.getChildren().add(PageHeader.LabelHeader("Create A New Hike"));
        topBox.getChildren().add(HikeLocationDate());
        
        HBox subBoxes = new HBox();
        subBoxes.getStyleClass().add("content");
        
        // add children
        subBoxes.getChildren().add(createTaskBox());
        subBoxes.getChildren().add(createReminderBox());
        
        // add footer
        HBox footer = new HBox();
        footer.getStyleClass().add("footer");
        footer.getChildren().add(footerBox());
        
        // add child objects to view
        root.setCenter(subBoxes);
        root.setTop(topBox);
        root.setBottom(footer);
        
        //newHike.getChildren().addAll(topBox, subBoxes);
        
        setLayout(new Scene(root));
        getLayout().getStylesheets().add("styles/main.css");
        getLayout().getStylesheets().add("styles/createHike.css");
        
        locationInput.requestFocus();
    }
    
    private HBox HikeLocationDate()
    {
        HBox locationBox = createLocationAndDateInputs();
        locationBox.getStyleClass().add("location");
        
        return locationBox;
    }
    
    private HBox createLocationAndDateInputs() {
        HBox locationBox = new HBox();
        locationBox.getStyleClass().add("location");
        
        Label locationLabel = new Label("Hike Location:");
        locationLabel.getStyleClass().add("input-label");
        
        /* Location */
        VBox locationInput = createLocationInputLayout();
        
        /* Date */
        Label dateLabel = new Label("Hike Date:");
        dateLabel.getStyleClass().add("input-label");
        hikeDatePicker = new DatePicker();
        
        locationBox.getChildren().addAll(locationLabel, locationInput,
                dateLabel, hikeDatePicker);
        
        return locationBox;
    }
    
    private VBox createLocationInputLayout(){
        VBox locationPanel = new VBox();
        
        locationInput = new TextField ();
        locationInput.getStyleClass().add("location-input");
        
        Label orStatement = new Label("or choose from a previous hike");
        
        ComboBox<String> visitedLocations = new ComboBox<>();
        visitedLocations.setMinWidth(locationInput.getWidth());
        
        visitedLocations.getItems().add("");
        visitedLocations.getItems().addAll(getUi().getController().getLocations());
        
        visitedLocations.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            locationInput.setText(newValue);
            locationInput.requestFocus();
        });
        
        locationPanel.getChildren().addAll(locationInput, orStatement, visitedLocations);
        
        return locationPanel;
    }
    
    private VBox createTaskBox()
    {
        VBox optionBox = new VBox();
        optionBox.getStyleClass().add("list-container");
        
        HBox titleBox = new HBox();
        titleBox.getStyleClass().add("list-container-header");
        
        Label titleLabel = new Label("Tasks");
        titleLabel.getStyleClass().add("list-container-header-label");
        
        titleBox.getChildren().add(titleLabel);
        optionBox.getChildren().add(titleBox);
        
        createTasksScrollPane(optionBox);
        
        return optionBox;
    }
    
    private void createTasksScrollPane(VBox optionBox) {
        ScrollPane taskPanel = createScrollPane(optionBox);
        
        VBox taskList = createTaskList(optionBox);
        
        taskPanel.vvalueProperty().bind(taskList.heightProperty());
        taskPanel.setContent(taskList);
    }
    
    private ScrollPane createScrollPane(VBox optionBox) {
        /* Task List */
        ScrollPane taskPanel = new ScrollPane();
        taskPanel.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        taskPanel.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        optionBox.getChildren().add(taskPanel);
        
       return taskPanel;
    }
    
    private VBox createTaskList(VBox optionBox) {
        VBox taskList = new VBox();
        taskList.getStyleClass().add("list-content");
        
        tasks = getUi().getController().getDefaultTasks();
        
        for(int i = 0; i < tasks.size(); i++) {
            createTaskItem(taskList, i);
        }
        
        HBox newItem = createNewTaskInput(taskList);
        
        optionBox.getChildren().add(newItem);
        
        return taskList;
    }
    
    private void createTaskItem(Pane parent, int id) {
        HBox taskBox = new HBox();
        taskBox.getStyleClass().add("list-item");
        
        /* This code is for the View Hikes. It's been tested */
//        CheckBox checkBox = new CheckBox();
//        checkBox.setId(String.valueOf(id));
//        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
//            getUi().getController().changeTaskStatus(id, newValue);
//            tasks.get(id).setChecked(newValue);
//        });
        
        Label taskLabel = new Label(tasks.get(id).getTaskMessage());
        taskLabel.getStyleClass().add("list-item-label");
        
        /* This is for when the label gets clicked, the checkbox wil check */
//        taskLabel.setOnMouseClicked(event -> {
//            checkBox.fire();
//        });
        
        taskBox.getChildren().addAll(taskLabel);
        parent.getChildren().add(taskBox);
    }
    
    private HBox createNewTaskInput(VBox taskList) {
        /* Add New Task */
        HBox newItem = new HBox();
        newItem.getStyleClass().add("new-entry");
        
        Label newItemLabel = new Label("Add a new Task");
        newItemLabel.getStyleClass().add("new-entry-label");
        
        TextField entryField = new TextField();
        entryField.getStyleClass().add("new-entry-input");
        
        entryField.setOnKeyPressed(key -> {
            if(key.getCode().equals(KeyCode.ENTER) ||
                    key.getCode().equals(KeyCode.TAB) &&
                            !entryField.getText().equals("")) {
                int newTaskId = tasks.size();
                tasks.add(new Task(newTaskId, false, entryField.getText()));
                
                createTaskItem(taskList, newTaskId);
                
                entryField.clear();
            }
        });
        
        newItem.getChildren().addAll(newItemLabel, entryField);
        
        return newItem;
    }
    
    private VBox createReminderBox()
    {
        VBox optionBox = new VBox();
        optionBox.getStyleClass().add("list-container");
        
        HBox titleBox = new HBox();
        titleBox.getStyleClass().add("list-container-header");
        
        Label titleLabel = new Label("Reminders");
        titleLabel.getStyleClass().add("list-container-header-label");
        
        titleBox.getChildren().add(titleLabel);
        optionBox.getChildren().add(titleBox);
        
        createReminderScrollPanel(optionBox);
        
        return optionBox;
    }
    
    private void createReminderScrollPanel(VBox optionBox) {
        /* Reminder List */
        ScrollPane reminderPanel = createScrollPane(optionBox);
        
        VBox reminderList = createReminderList(optionBox);
        
        reminderPanel.vvalueProperty().bind(reminderList.heightProperty());
        reminderPanel.setContent(reminderList);
    }
    
    private VBox createReminderList(VBox optionBox) {
        VBox reminderList = new VBox();
        reminderList.getStyleClass().add("list-content");
        
        reminders =  getUi().getController().getReminders();
        
        for(int i = 0; i < reminders.size(); i++) {
            createReminderItem(reminderList, i);
        }
        
        HBox newItem = createNewReminderInput(reminderList);
        optionBox.getChildren().add(newItem);
        
        return reminderList;
    }
    
    private void createReminderItem(Pane parent, int id) {
        HBox reminderBox = new HBox();
        reminderBox.getStyleClass().add("list-item");
        
        Label reminderLabel = new Label(reminders.get(id).getReminderMessage());
        reminderLabel.getStyleClass().add("list-item-label");
        
        reminderBox.getChildren().add(reminderLabel);
        
        parent.getChildren().add(reminderBox);
    }
    
    private HBox createNewReminderInput(VBox reminderList) {
        /* Add New Reminder */
        HBox newItem = new HBox();
        newItem.getStyleClass().add("new-entry");
        
        Label newItemLabel = new Label("Add a new Reminder");
        newItemLabel.getStyleClass().add("new-entry-label");
        
        TextField entryField = new TextField();
        entryField.getStyleClass().add("new-entry-input");
        
        entryField.setOnKeyPressed(key -> {
            if(key.getCode().equals(KeyCode.ENTER) ||
                    key.getCode().equals(KeyCode.TAB) &&
                            !entryField.getText().equals("")) {
                int newReminderId = reminders.size();
                reminders.add(new Reminder(reminders.size(), entryField.getText()));
                
                createReminderItem(reminderList, newReminderId);
                
                entryField.clear();
            }
        });
        
        newItem.getChildren().addAll(newItemLabel, entryField);
        
        return newItem;
    }
    
    private HBox footerBox()
    {
        HBox footerBox = new HBox();
        footerBox.setPrefHeight(50);
        footerBox.setAlignment(Pos.BOTTOM_RIGHT);
        footerBox.setPadding(new Insets(5));
        
        Button submitBtn = new Button();
        submitBtn.setText("Submit");
        submitBtn.getStyleClass().add("footer-btn");
        
        // do set on action for submit button
        submitBtn.setOnMouseClicked((event -> {
            if(!locationInput.getText().equals("")) {
                sceneInput.put("location", locationInput.getText());
            }
            if (hikeDatePicker.getValue() != null) {
                sceneInput.put("datePlanned", hikeDatePicker.getValue().toString());
            }
            
            if (sceneInput.isEmpty()) {
                
                System.out.println("No input was entered.");
            }
            if(sceneInput.size() < 2) {
                System.out.println("Missing fields.");
            } else {
                getUi().getController().createNewHike(sceneInput, tasks, reminders);
                getUi().goToScene(new ViewHikesScene(getUi()));
            }
        }));
        
        Button cancelBtn =  new Button();
        cancelBtn.setText("Cancel");
        cancelBtn.getStyleClass().add("footer-btn");
        
        // do set on action for cancel button
        cancelBtn.setOnMouseClicked(event -> {
            getUi().goToScene(new HomeScene(getUi()));
        });
        
        footerBox.getChildren().addAll(submitBtn, cancelBtn);
        
        return footerBox;
    }
}
