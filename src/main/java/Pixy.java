import java.util.Scanner;

// single level of abstraction to be implemented

public class Pixy {

    // Message Repo
    private static final String MESSAGE_HELLO = "Hello! I'm Pixy\nWhat can I do for you?";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];

        System.out.println(MESSAGE_HELLO);
        String input = in.nextLine();
        String command = input.trim().split(" ")[0];
        int tasksCounter = 0;

        while (!command.equals("bye")) {
            input = in.nextLine();
            command = input.trim().split(" ")[0];
            switch (command) {
            case "list" -> {
                printList(tasksCounter, tasks);
            }
            case "unmark" -> {
                unmarkTask(input, tasks);
            }
            case "mark" -> {
                markTask(input, tasks);
            }
            default -> {
                switch (command) {
                    case "todo" -> {
                        String description = input.substring(input.indexOf(" ") + 1);
                        tasks[tasksCounter] = new Todo(description);
                        tasksCounter++;
                    }
                    case "deadline" -> {
                        String[] deadlineTask = input.substring(9).split(" /by ", 2);
                        tasks[tasksCounter] = new Deadline(deadlineTask[0], deadlineTask[1]);
                        tasksCounter++;
                    }
                    case "event" -> {
                        String[] eventTask = input.substring(6).split(" /from ", 2);
                        String[] eventTime = eventTask[1].split(" /to ", 2);
                        tasks[tasksCounter] = new Event(eventTask[0], eventTime[0], eventTime[1]);
                        tasksCounter++;
                    }
                    default -> {
                        System.out.println("not a command!");
                    }
                }
            }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void markTask(String input, Task[] tasks) {
        int taskIndex = Integer.parseInt(input.substring(5));
        tasks[taskIndex - 1].setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[taskIndex - 1].toString());
    }

    private static void unmarkTask(String input, Task[] tasks) {
        int taskIndex = Integer.parseInt(input.substring(7));
        tasks[taskIndex - 1].setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks[taskIndex - 1].toString());
    }

    private static void printList(int tasksCounter, Task[] tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksCounter; i++) {
            System.out.println(tasks[i].toString());
        }
    }
}
