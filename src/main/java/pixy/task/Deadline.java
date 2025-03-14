package pixy.task;

/**
 * Represents a task with a deadline.
 * Inherits from the Task class.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a new Deadline task with the given description and deadline.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        System.out.println("deadline added: " + this);
    }

    /**
     * Constructs a new Deadline object with the given description, due date, and completion status.
     *
     * @param description the description of the deadline
     * @param by the due date of the deadline
     * @param isDone the completion status of the deadline
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    protected String getTaskType() {
        return "D";
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %d | %s | %s", getTaskType(), isDone ? 1 : 0, description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}