package controller;

import javafx.application.Application;
import model.*;
import ui.UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Controller {
    
    private Model model;
    
    ArrayList<Hike> hikes = new ArrayList<>();
    
    public Controller() {
        this.model = new Model();
    }
    
    public static void main(String[] args) {
        Application.launch(UI.class, args);
    }
    
    public void createNewHike(HashMap<String, String> newHikeInput,
                              List<Task> tasks, List<Reminder> reminders) {
        hikes.add(model.addHike(newHikeInput, tasks, reminders));
    }
    
    public List<Hike> getHikes() {
        return model.getHikes();
    }
    
    public List<Hike> getPlannedHikes() {
        return model.getPlannedHikes();
    }
    
    public List<Task> getDefaultTasks(){
        return model.getDefaultTasks();
    }
    
    public List<Reminder> getReminders(){
        return model.getDefaultReminders();
    }
    
    public HashSet<String> getLocations() {
        return model.getLocations();
    }
    
    public void changeTaskStatus(int taskId, Boolean newValue) {
        System.out.println("Task " + taskId + " was changed to " + newValue);
    }
}
