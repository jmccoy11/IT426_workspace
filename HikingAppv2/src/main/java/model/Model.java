package model;

import java.io.*;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;

public class Model {
    private final File LOCATIONS_FILE = new File("src/main/java/files/locations.txt");
    private final File HIKES_FILE = new File("src/main/java/files/hikes.txt");
    private final File REMINDERS_FILE = new File("src/main/java/files/reminders.txt");
    private final File TASKS_FILE = new File("src/main/java/files/tasks.txt");
    
    private List<Reminder> defaultReminders;
    private List<Task> defaultTasks;
    private HashSet<String> locations;
    private List<Hike> hikes;
    
    public Model() {
        defaultReminders = createDefaultReminders();
        defaultTasks = createDefaultTasks();
        locations = createLocationsSet();
        hikes = createHikesList();
    }
    
    private ArrayList<String> getListFromFile(File fileToBeRead)
    {
        ArrayList<String> listFromFile = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToBeRead))) {
            String line;
            while ((line = reader.readLine()) != null) {
                listFromFile.add(line);
            }
        } catch (IOException e)
        {
            System.out.println(e + ": ERROR");
        }
        
        return listFromFile;
    }
    
    private ArrayList<Reminder> createDefaultReminders(){
        ArrayList<String> loadedReminders = getListFromFile(REMINDERS_FILE);
        ArrayList<Reminder> reminders = new ArrayList<>();
        
        for(String reminder : loadedReminders) {
            reminders.add(new Reminder(reminders.size(), reminder));
        }
        
        return reminders;
    }
    
    public List<Reminder> getDefaultReminders() {
        return defaultReminders;
    }
    
    public void addDefaultReminder(String reminder) {
        int newReminderIndex = defaultReminders.size();
        this.defaultReminders.add(new Reminder(newReminderIndex, reminder));
        addToEndOfFile(REMINDERS_FILE, defaultReminders.get(newReminderIndex).getReminderMessage());
    }
    
    private ArrayList<Task> createDefaultTasks() {
        ArrayList<String> loadedTasks = getListFromFile(TASKS_FILE);
        ArrayList<Task> tasks = new ArrayList<>();
        
        for(String task : loadedTasks) {
            tasks.add(new Task(tasks.size(), false, task));
        }
        
        return tasks;
    }
    
    public List<Task> getDefaultTasks() {
        return defaultTasks;
    }
    
    public void addDefaultTask(String task) {
        int newTaskIndex = defaultTasks.size();
        this.defaultTasks.add(new Task(newTaskIndex, false, task));
        addToEndOfFile(TASKS_FILE, defaultTasks.get(newTaskIndex).getTaskMessage());
    }
    
    private HashSet<String> createLocationsSet() {
        ArrayList<String> loadedLocations = getListFromFile(LOCATIONS_FILE);
        HashSet<String> locationsSet = new HashSet<>(loadedLocations);
        
        return locationsSet;
    }
    
    public HashSet<String> getLocations() {
        return locations;
    }
    
    private List<Hike> createHikesList() {
        ArrayList<String> loadedHikes = getListFromFile(HIKES_FILE);
        ArrayList<Hike> createdHikes = new ArrayList<>();
    
        HashMap<String, String> loadedHikeDetails = new HashMap<>();
        ArrayList<Integer> heartRateReadings = new ArrayList<>();
        ArrayList<Reminder> loadedReminders = new ArrayList<>();
        ArrayList<Task> loadedTasks = new ArrayList<>();
        
        for(String hike : loadedHikes) {
            String[] firstBreak = hike.split(", reminders=\\[");
            
            String[] secondBreak = firstBreak[0].split("\\{");
            String[] hikeDetails = secondBreak[1].split(", ");
            for(int i = 0; i < hikeDetails.length; i++) {
                String[] detail = hikeDetails[i].split("=");
                if(detail[0].equals("location") || detail[0].equals("datePlanned")) {
                    detail[1] = detail[1].replaceAll("\'", "");
                } else if (detail[0].equals("heartRateReadings")) {
                    String readings = detail[1].substring(1, detail[1].length()-1);
                    if(readings.length() > 0) {
                        String[] readingsSplit = readings.split(", ");
                        for(int j = 0; j < readingsSplit.length; j++) {
                            heartRateReadings.add(Integer.valueOf(readingsSplit[j]));
                        }
                    }
                }
                loadedHikeDetails.put(detail[0], detail[1]);
            }
            
            String remindersAndTasks = firstBreak[1];
            firstBreak = remindersAndTasks.split("}], tasks=\\[");
            String remindersData = firstBreak[0];
            String tasksData = (firstBreak[1]);
    
            Pattern regexPattern = Pattern.compile("(Reminder\\{|}, )");
            String[] parts = regexPattern.split(remindersData);
            for(int i = 0; i < parts.length; i++) {
                if(!parts[i].equals("")) {
                    String[] split = parts[i].split("=\'");
                    split[1] = split[1].substring(0, split[1].length()-1);
                    loadedReminders.add(new Reminder(loadedReminders.size(), split[1]));
                }
            }
    
            tasksData = tasksData.substring(0, tasksData.length()-2);
            regexPattern = Pattern.compile("(Task\\{|}, )");
            parts = regexPattern.split(tasksData);
            for(int i = 0; i < parts.length; i++) {
                if(!parts[i].equals("")) {
                    String[] split = parts[i].split(", ");
                    Boolean checked = Boolean.valueOf(split[1].split("=")[1]);
                    String message = split[2].split("=")[1];
                    loadedTasks.add(new Task(loadedTasks.size(), checked, message.substring(1, message.length()-1)));
                }
            }
            
            Hike newHike = new Hike(createdHikes.size(), loadedHikeDetails, loadedReminders,
                    loadedTasks, heartRateReadings);
            createdHikes.add(newHike);
        }
        
        return createdHikes;
    }
    
    public List<Hike> getHikes() {
        return hikes;
    }
    
    public List<Hike> getPlannedHikes() {
        List<Hike> upcomingHikes = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for(Hike hike : hikes) {
            try {
                Date input = new SimpleDateFormat("yyyy-MM-dd")
                        .parse(hike.getDatePlanned());
                LocalDate hikeDate = input.toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate();
                if(hikeDate.isAfter(now)) {
                    upcomingHikes.add(hike);
                }
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }
        
        return upcomingHikes;
    }
    
    public Hike addHike(HashMap<String, String> newHikeInput,
                        List<Task> tasks, List<Reminder> reminders) {
        Hike newHike = new Hike(hikes.size(), newHikeInput.get("location"),
                newHikeInput.get("datePlanned"), new ArrayList<>(reminders),
                new ArrayList<>(tasks));
        
        addToEndOfFile(HIKES_FILE, newHike.toString());
        
        if(!locations.contains(newHikeInput.get("location"))) {
            String location = newHikeInput.get("location");
            locations.add(location);
            addToEndOfFile(LOCATIONS_FILE, location);
        }
        
        hikes.add(newHike);
        
        return newHike;
    }
    
    private void addToEndOfFile(File fileToAddToEnd, String lineToAdd)
    {
        lineToAdd += "\n";
        try
        {
            Writer outputLineWriter = new BufferedWriter(new FileWriter(fileToAddToEnd, true));
            outputLineWriter.append(lineToAdd);
            outputLineWriter.close();
        } catch (IOException e)
        {
            System.out.println("UHOH");
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        return "Model{" +
                "LOCATIONS_FILE=" + LOCATIONS_FILE +
                ", HIKES_FILE=" + HIKES_FILE +
                ", REMINDERS_FILE=" + REMINDERS_FILE +
                ", TASKS_FILE=" + TASKS_FILE +
                ", defaultReminders=" + defaultReminders +
                ", defaultTasks=" + defaultTasks +
                ", locations=" + locations +
                ", hikes=" + hikes +
                '}';
    }
}
