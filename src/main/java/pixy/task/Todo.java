package pixy.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        System.out.println("todo added: " + this);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    protected String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}