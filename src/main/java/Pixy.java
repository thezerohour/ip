import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Pixy {

    private static final String MESSAGE_HELLO = "Hello! I'm Pixy\nWhat can I do for you?";
    private static final String MESSAGE_EMPTY_DESCRIPTION = "empty description >:(";
    private static final String MESSAGE_INVALID_TIME = "invalid time >:(";
    private static final String MESSAGE_INVALID_NUMBER = "invalid number..?";
    private static final List<Task> tasks = new ArrayList<>();
    private static boolean isRunning = true;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println(MESSAGE_HELLO);

        while (isRunning) {
            try {
                String input = in.nextLine();
                String command = input.trim().split(" ")[0];
                switch (command) {
                case "list" -> printList();
                case "unmark" -> unmarkTask(input);
                case "mark" -> markTask(input);
                case "todo" -> addTodo(input);
                case "deadline" -> addDeadline(input);
                case "event" -> addEvent(input);
                case "bye" -> isRunning = false;
                default -> invalidCommand();
                }
            } catch (PixyException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(MESSAGE_INVALID_NUMBER);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void invalidCommand() {
        System.out.println("not a command!");
    }

    private static void addEvent(String input) throws PixyException {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new PixyException(MESSAGE_INVALID_TIME);
        }
        String[] eventTask = input.substring(6).split(" /from ", 2);
        String[] eventTime = eventTask[1].split(" /to ", 2);
        tasks.add(new Event(eventTask[0], eventTime[0], eventTime[1]));
    }

    private static void addDeadline(String input) throws PixyException {
        if (!input.contains(" /by ")) {
            throw new PixyException(MESSAGE_INVALID_TIME);
        }
        String[] deadlineTask = input.substring(9).split(" /by ", 2);
        tasks.add(new Deadline(deadlineTask[0], deadlineTask[1]));
    }

    private static void addTodo(String input) throws PixyException {
        String description = input.substring(input.indexOf(" ") + 1);
        if (description.isBlank()) {
            throw new PixyException(MESSAGE_EMPTY_DESCRIPTION);
        }
        tasks.add(new Todo(description));
    }

    private static void markTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(5));
        Task task = tasks.get(taskIndex - 1);
        task.setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    private static void unmarkTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(7));
        Task task = tasks.get(taskIndex - 1);
        task.setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }
}
