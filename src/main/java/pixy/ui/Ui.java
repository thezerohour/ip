package pixy.ui;

import java.util.Scanner;

/**
 * The `Ui` class represents the user interface of the Pixy application.
 * It provides methods for interacting with the user, displaying messages, and reading user input.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Pixy\nWhat can I do for you?");
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}