package pixy.ui;

import pixy.parser.Parser;
import pixy.storage.Storage;
import pixy.task.TaskList;

/**
 * The `Pixy` class represents the main entry point for the Pixy application.
 * It handles the user interface, task management, and file storage.
 */
public class Pixy {

    private static final String MESSAGE_INVALID_NUMBER = "invalid number..?";
    private static final String FILE_PATH = "./data/pixy.txt";
    private static boolean isRunning = true;
    private static final Storage storage = new Storage(FILE_PATH);

    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();

        storage.loadTasksFromFile(taskList.getTasks());

        ui.showWelcomeMessage();

        while (isRunning) {
            try {
                String input = ui.readCommand();
                if (input.trim().equals("bye")) {
                    isRunning = false;
                } else {
                    Parser.parseCommand(input, taskList);
                }
            } catch (PixyException e) {
                ui.showErrorMessage(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showErrorMessage(MESSAGE_INVALID_NUMBER);
            }
        }
        ui.showGoodbyeMessage();
        storage.saveTasksToFile(taskList.getTasks());
    }
}
