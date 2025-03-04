package pixy.task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        System.out.println("event added: " + this);
    }

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