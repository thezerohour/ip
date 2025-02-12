public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        System.out.println("event added: " + this);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (from: " + from + ", to: " + to + ")";
    }
}
