package model;

public class Task {
    private int taskId;
    private boolean checked;
    private String taskMessage;
    
    public Task(int taskId, boolean checked, String taskMessage) {
        this.taskId = taskId;
        this.checked = checked;
        this.taskMessage = taskMessage;
    }
    
    public int getTaskId() {
        return taskId;
    }
    
    public boolean isChecked() {
        return checked;
    }
    
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
    public String getTaskMessage() {
        return taskMessage;
    }
    
    public void setTaskMessage(String taskMessage) {
        this.taskMessage = taskMessage;
    }
    
    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", checked=" + checked +
                ", taskMessage='" + taskMessage + '\'' +
                '}';
    }
}
