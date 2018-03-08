package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Hike {
    private int hikeId;
    private String location;
    private String datePlanned;
    private Boolean completed;
    
    private Integer heartRateAverage;
    private ArrayList<Integer> heartRateReadings;
    private Integer stepsTaken;
    
    private ArrayList<Reminder> reminders;
    private ArrayList<Task> tasks;
    
    public Hike(int hikeId, String location, String datePlanned, ArrayList<Reminder> reminders,
                ArrayList<Task> tasks) {
        this.hikeId = hikeId;
        this.location = location;
        this.datePlanned = datePlanned;
        this.reminders = reminders;
        this.tasks = tasks;
        
        this.heartRateReadings = new ArrayList<>();
        this.heartRateAverage = 0;
        this.stepsTaken = 0;
        this.completed = false;
    }
    
    public Hike(int hikeId, HashMap<String, String> loadedHikeDetails, ArrayList<Reminder> reminders,
                ArrayList<Task> tasks, ArrayList<Integer> heartRateReadings) {
        this.hikeId = hikeId;
        this.location = loadedHikeDetails.get("location");
        this.datePlanned = loadedHikeDetails.get("datePlanned");
        this.reminders = reminders;
        this.tasks = tasks;
    
        this.heartRateReadings = heartRateReadings;
        this.heartRateAverage = Integer.valueOf(loadedHikeDetails.get("heartRateAverage"));
        this.stepsTaken = Integer.valueOf(loadedHikeDetails.get("stepsTaken"));
        this.completed = Boolean.valueOf(loadedHikeDetails.get("completed"));
    }
    
    public ArrayList<Reminder> getReminders() {
        return reminders;
    }
    
    public void setReminders(ArrayList<Reminder> reminders) {
        this.reminders = reminders;
    }
    
    public void addReminder(Reminder reminder) {
        this.reminders.add(reminder);
    }
    
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public void addTask(Task task) {
        this.tasks.add(task);
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public Integer getHeartRateAverage() {
        return heartRateAverage;
    }
    
    public void setHeartRateAverage(Integer heartRateAverage) {
        this.heartRateAverage = heartRateAverage;
    }
    
    public Integer getStepsTaken() {
        return stepsTaken;
    }
    
    public ArrayList<Integer> getHeartRateReadings() {
        return heartRateReadings;
    }
    
    public void setHeartRateReadings(ArrayList<Integer> heartRateReadings) {
        this.heartRateReadings = heartRateReadings;
    }
    
    public void addHeartRateReading(Integer reading) {
        this.heartRateReadings.add(reading);
    }
    
    public void setStepsTaken(Integer stepsTaken) {
        this.stepsTaken = stepsTaken;
    }
    
    public String getDatePlanned() {
        return datePlanned;
    }
    
    public void setDatePlanned(String datePlanned) {
        this.datePlanned = datePlanned;
    }
    
    public Boolean getCompleted() {
        return completed;
    }
    
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    
    @Override
    public String toString() {
        return "Hike{" +
                "hikeId=" + hikeId +
                ", location='" + location + '\'' +
                ", datePlanned='" + datePlanned + '\'' +
                ", completed=" + completed +
                ", heartRateAverage=" + heartRateAverage +
                ", heartRateReadings=" + heartRateReadings +
                ", stepsTaken=" + stepsTaken +
                ", reminders=" + reminders +
                ", tasks=" + tasks +
                '}';
    }
    
//    public String writeToFileToString() {
//        String writeString = "";
//        writeString = hikeId + '\t' + location + '\t' + datePlanned + '\t' + completed +
//                '\t' + heartRateAverage + '\t' + heartRateReadings + '\t' + stepsTaken +
//                "|reminders|";
//
//        for(Reminder reminder : reminders) {
//            writeString += reminder.reminderMessage;
//        }
//
//        writeString += "|tasks|";
//
//        for (Task task : tasks) {
//
//        }
//    }
}