package pixy.task;

/**
 * Represents a todo task.
 * Inherits from the Task class.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo object with the given description.
     *
     * @param description the description of the Todo
     */
    public Todo(String description) {
        super(description);
        System.out.println("todo added: " + this);
    }

    /**
     * Constructs a new Todo object with the given description and completion status.
     *
     * @param description the description of the todo
     * @param isDone      the completion status of the todo
     */
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