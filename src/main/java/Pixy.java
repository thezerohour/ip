import java.util.Scanner;

public class Pixy {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];

        System.out.println("Hello! I'm Pixy");
        System.out.println("What can I do for you?");
        String command = in.nextLine();
        int tasksCounter = 0;
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasksCounter; i++) {
                    System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                }
            }
            else if (command.contains("unmark")) {
                int taskIndex = Integer.parseInt(command.substring(7));
                tasks[taskIndex - 1].setDone(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println((taskIndex) + ".[" + tasks[taskIndex - 1].getStatusIcon() + "] " + tasks[taskIndex - 1].description);
            }
            else if (command.contains("mark")) {
                int taskIndex = Integer.parseInt(command.substring(5));
                tasks[taskIndex - 1].setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println((taskIndex) + ".[" + tasks[taskIndex - 1].getStatusIcon() + "] " + tasks[taskIndex - 1].description);
            }
            else {
                System.out.println("added: " + command);
                tasks[tasksCounter] = new Task(command);
                tasksCounter++;
            }
            command = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
