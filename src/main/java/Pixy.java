import java.util.Scanner;

public class Pixy {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] items = new String[100];

        System.out.println("Hello! I'm Pixy");
        System.out.println("What can I do for you?");
        String command = in.nextLine();
        int counter = 0;
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + ". " + items[i]);
                }
            } else {
                System.out.println("added: " + command);
                items[counter] = command;
                counter++;
            }
            command = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
