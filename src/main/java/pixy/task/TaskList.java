package pixy.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object with the given list of tasks.
     *
     * @param tasks the list of tasks to be stored in the TaskList
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index from the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the task at the specified index as done or not done.
     *
     * @param index the index of the task to be marked
     * @param isDone the status indicating whether the task is done or not
     */
    public void markTask(int index, boolean isDone) {
        Task task = tasks.get(index);
        task.setDone(isDone);
    }

    /**
     * Prints all the tasks in the task list.
     */
    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }

    public void searchTasks(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                System.out.println(task.toString());
            }
        }
    }
}