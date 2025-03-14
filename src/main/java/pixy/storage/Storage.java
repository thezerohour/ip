package pixy.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import pixy.task.Task;
import pixy.task.Todo;
import pixy.task.Deadline;
import pixy.task.Event;

/**
 * The `Storage` class is responsible for saving and loading tasks to/from a file.
 * It provides methods to save a list of tasks to a specified file path and load tasks from an existing file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the given list of tasks to a file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasksToFile(List<Task> tasks) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Create directories if they do not exist
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Task task : tasks) {
                    writer.write(task.toFileFormat());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from a file and adds them to the provided list of tasks.
     *
     * @param tasks The list of tasks to add the loaded tasks to.
     */
    public void loadTasksFromFile(List<Task> tasks) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return; // If the file does not exist, there's nothing to load
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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