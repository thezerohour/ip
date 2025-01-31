import java.util.Scanner;

public class Pixy {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Pixy");
        System.out.println("What can I do for you?");
        String command = in.nextLine();
        while (!command.equals("bye")) {
            System.out.println(command);
            command = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
