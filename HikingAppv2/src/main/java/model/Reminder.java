package model;

public class Reminder {
    int reminderId;
    String reminderMessage;
    
    public Reminder(int reminderId, String reminderMessage) {
        this.reminderId = reminderId;
        this.reminderMessage = reminderMessage;
    }
    
    public int getReminderId() {
        return reminderId;
    }
    
    public String getReminderMessage() {
        return reminderMessage;
    }
    
    public void setReminderMessage(String reminderMessage) {
        this.reminderMessage = reminderMessage;
    }
    
    @Override
    public String toString() {
        return "Reminder{" +
                "reminderId=" + reminderId +
                ", reminderMessage='" + reminderMessage + '\'' +
                '}';
    }
}
