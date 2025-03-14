package pixy.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void markTask(int index, boolean isDone) {
        Task task = tasks.get(index);
        task.setDone(isDone);
    }

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