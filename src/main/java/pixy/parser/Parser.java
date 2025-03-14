package pixy.parser;

import pixy.ui.PixyException;
import pixy.task.Deadline;
import pixy.task.Event;
import pixy.task.TaskList;
import pixy.task.Todo;

/**
 * The Parser class is responsible for parsing user commands and executing corresponding actions on the TaskList.
 */
public class Parser {
    private static final String MESSAGE_INVALID_TIME = "invalid time >:(";
    private static final String MESSAGE_EMPTY_DESCRIPTION = "empty description >:(";

    /**
     * Parses the user input and executes the corresponding command.
     *
     * @param input the user input to be parsed
     * @param taskList the task list to be modified
     * @throws PixyException if an error occurs during parsing or execution of the command
     */
    public static void parseCommand(String input, TaskList taskList) throws PixyException {
        String command = input.trim().split(" ")[0];
        switch (command) {
        case "list" -> taskList.printTasks();
        case "unmark" -> unmarkTask(input, taskList);
        case "mark" -> markTask(input, taskList);
        case "delete" -> deleteTask(input, taskList);
        case "todo" -> addTodo(input, taskList);
        case "deadline" -> addDeadline(input, taskList);
        case "event" -> addEvent(input, taskList);
        case "find" -> findTasks(input, taskList);
        default -> invalidCommand();
        }
    }

    /**
     * Deletes a task from the task list based on the given input.
     *
     * @param input the input string containing the task index to be deleted
     * @param taskList the task list from which the task will be deleted
     */
    private static void deleteTask(String input, TaskList taskList) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.deleteTask(taskIndex));
    }

    private static void invalidCommand() {
        System.out.println("not a command!");
    }

    /**
     * Adds an event task to the task list.
     *
     * @param input the input string containing the event task details
     * @param taskList the task list to add the event task to
     * @throws PixyException if the input string does not contain "/from" or "/to"
     */
    private static void addEvent(String input, TaskList taskList) throws PixyException {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new PixyException(MESSAGE_INVALID_TIME);
        }
        String[] eventTask = input.substring(6).split(" /from ", 2);
        String[] eventTime = eventTask[1].split(" /to ", 2);
        taskList.addTask(new Event(eventTask[0], eventTime[0], eventTime[1]));
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param input the input string containing the task details and deadline
     * @param taskList the task list to add the deadline task to
     * @throws PixyException if the input string does not contain the "/by" delimiter
     */
    private static void addDeadline(String input, TaskList taskList) throws PixyException {
        if (!input.contains(" /by ")) {
            throw new PixyException(MESSAGE_INVALID_TIME);
        }
        String[] deadlineTask = input.substring(9).split(" /by ", 2);
        taskList.addTask(new Deadline(deadlineTask[0], deadlineTask[1]));
    }

    /**
     * Adds a new Todo task to the task list.
     *
     * @param input the input string containing the description of the task
     * @param taskList the task list to add the task to
     * @throws PixyException if the description is empty
     */
    private static void addTodo(String input, TaskList taskList) throws PixyException {
        String description = input.substring(input.indexOf(" ") + 1);
        if (description.isBlank()) {
            throw new PixyException(MESSAGE_EMPTY_DESCRIPTION);
        }
        taskList.addTask(new Todo(description));
    }

    /**
     * Marks a task as done in the task list.
     *
     * @param input the input string containing the task index
     * @param taskList the task list to mark the task in
     */
    private static void markTask(String input, TaskList taskList) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        taskList.markTask(taskIndex, true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.getTask(taskIndex));
    }

    /**
     * Unmarks a task as not done yet.
     *
     * @param input the input string containing the task index
     * @param taskList the task list to modify
     */
    private static void unmarkTask(String input, TaskList taskList) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        taskList.markTask(taskIndex, false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.getTask(taskIndex));
    }

    /**
     * Finds tasks in the given input and searches for them in the task list.
     *
     * @param input The input string containing the keyword to search for tasks.
     * @param taskList The task list to search for tasks in.
     */
    private static void findTasks(String input, TaskList taskList) {
        String keyword = input.substring(5).trim();
        taskList.searchTasks(keyword);
    }
}