package pixy.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String toFileFormat() {
        return String.format("%s | %d | %s", getTaskType(), isDone ? 1 : 0, description);
    }

    protected String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}