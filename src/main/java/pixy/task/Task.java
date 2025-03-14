package pixy.task;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task object with the given description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task object with the given description and completion status.
     *
     * @param description the description of the task
     * @param isDone      the completion status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task. It is represented by "[X]" if the task is done, and "[ ]" if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Sets the status of the task to indicate whether it is done or not.
     *
     * @param done the boolean value indicating whether the task is done or not
     */
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