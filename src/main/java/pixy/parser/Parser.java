package pixy.parser;

import pixy.ui.PixyException;
import pixy.task.Deadline;
import pixy.task.Event;
import pixy.task.TaskList;
import pixy.task.Todo;

public class Parser {
    private static final String MESSAGE_INVALID_TIME = "invalid time >:(";
    private static final String MESSAGE_EMPTY_DESCRIPTION = "empty description >:(";

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

    private static void deleteTask(String input, TaskList taskList) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.deleteTask(taskIndex));
    }

    private static void invalidCommand() {
        System.out.println("not a command!");
    }

    private static void addEvent(String input, TaskList taskList) throws PixyException {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new PixyException(MESSAGE_INVALID_TIME);
        }
        String[] eventTask = input.substring(6).split(" /from ", 2);
        String[] eventTime = eventTask[1].split(" /to ", 2);
        taskList.addTask(new Event(eventTask[0], eventTime[0], eventTime[1]));
    }

    private static void addDeadline(String input, TaskList taskList) throws PixyException {
        if (!input.contains(" /by ")) {
            throw new PixyException(MESSAGE_INVALID_TIME);
        }
        String[] deadlineTask = input.substring(9).split(" /by ", 2);
        taskList.addTask(new Deadline(deadlineTask[0], deadlineTask[1]));
    }

    private static void addTodo(String input, TaskList taskList) throws PixyException {
        String description = input.substring(input.indexOf(" ") + 1);
        if (description.isBlank()) {
            throw new PixyException(MESSAGE_EMPTY_DESCRIPTION);
        }
        taskList.addTask(new Todo(description));
    }

    private static void markTask(String input, TaskList taskList) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        taskList.markTask(taskIndex, true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.getTask(taskIndex));
    }

    private static void unmarkTask(String input, TaskList taskList) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        taskList.markTask(taskIndex, false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.getTask(taskIndex));
    }

    private static void findTasks(String input, TaskList taskList) {
        String keyword = input.substring(5).trim();
        taskList.searchTasks(keyword);
    }
}