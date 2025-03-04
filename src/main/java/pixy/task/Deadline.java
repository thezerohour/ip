package pixy.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        System.out.println("deadline added: " + this);
    }

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