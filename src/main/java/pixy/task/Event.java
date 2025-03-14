package pixy.task;

/**
 * Represents an event task that extends the Task class.
 * An event task has a description, a start time, and an end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new Event object with the given description, start time, and end time.
     *
     * @param description the description of the event
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        System.out.println("event added: " + this);
    }

    /**
     * Constructs a new Event object with the given description, start time, end time, and completion status.
     *
     * @param description the description of the event
     * @param from the start time of the event
     * @param to the end time of the event
     * @param isDone the completion status of the event
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    protected String getTaskType() {
        return "E";
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %d | %s | %s | %s", getTaskType(), isDone ? 1 : 0, description, from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ", to: " + to + ")";
    }
}