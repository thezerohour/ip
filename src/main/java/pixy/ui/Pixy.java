package pixy.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pixy.task.Deadline;
import pixy.task.Event;
import pixy.task.Task;
import pixy.task.Todo;

public class Pixy {

    private static final String MESSAGE_HELLO = "Hello! I'm Pixy\nWhat can I do for you?";
    private static final String MESSAGE_EMPTY_DESCRIPTION = "empty description >:(";
    private static final String MESSAGE_INVALID_TIME = "invalid time >:(";
    private static final String MESSAGE_INVALID_NUMBER = "invalid number..?";
    private static final String FILE_PATH = "./data/pixy.txt";
    private static final List<Task> tasks = new ArrayList<>();
    private static boolean isRunning = true;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        loadTasksFromFile();

        System.out.println(MESSAGE_HELLO);

        while (isRunning) {
            try {
                String input = in.nextLine();
                String command = input.trim().split(" ")[0];
                switch (command) {
                case "list" -> printList();
                case "unmark" -> unmarkTask(input);
                case "mark" -> markTask(input);
                case "delete" -> deleteTask(input);
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
        saveTasksToFile();
    }

    private static void deleteTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(7));
        Task task = tasks.remove(taskIndex - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
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
        saveTasksToFile();
    }

    private static void addDeadline(String input) throws PixyException {
        if (!input.contains(" /by ")) {
            throw new PixyException(MESSAGE_INVALID_TIME);
        }
        String[] deadlineTask = input.substring(9).split(" /by ", 2);
        tasks.add(new Deadline(deadlineTask[0], deadlineTask[1]));
        saveTasksToFile();
    }

    private static void addTodo(String input) throws PixyException {
        String description = input.substring(input.indexOf(" ") + 1);
        if (description.isBlank()) {
            throw new PixyException(MESSAGE_EMPTY_DESCRIPTION);
        }
        tasks.add(new Todo(description));
        saveTasksToFile();
    }

    private static void markTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(5));
        Task task = tasks.get(taskIndex - 1);
        task.setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        saveTasksToFile();
    }

    private static void unmarkTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(7));
        Task task = tasks.get(taskIndex - 1);
        task.setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        saveTasksToFile();
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }

    private static void saveTasksToFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.getParentFile().mkdirs()) {
                System.out.println("Couldn't create directory " + FILE_PATH);
            } // Create directories if they do not exist
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (Task task : tasks) {
                    writer.write(task.toFileFormat());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    private static void loadTasksFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return; // If the file does not exist, there's nothing to load
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    switch (type) {
                    case "T" -> tasks.add(new Todo(description, isDone));
                    case "D" -> {
                        String by = parts[3];
                        tasks.add(new Deadline(description, by, isDone));
                    }
                    case "E" -> {
                        String from = parts[3];
                        String to = parts[4];
                        tasks.add(new Event(description, from, to, isDone));
                    }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }
    }
}