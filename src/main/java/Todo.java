public class Todo extends Task {
    public Todo(String description) {
        super(description);
        System.out.println("added: " + this);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
