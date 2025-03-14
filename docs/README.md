# Pixy User Guide

```
Hello! I'm Pixy
What can I do for you?
```

Pixy allows you to manage your tasks with ease, while having an anti-border tendency.

## Adding ToDos

`todo {taskName}`

Adds a todo task with the specified task name.

```
todo added: [T][ ] add new task
```

## Adding Deadlines

`deadline {taskName} /by {deadline}`

Adds a deadline task with the specified task name and deadline.

```
deadline added: [D][ ] add new deadline (by: yesterday)
```

## Adding Events

`event {eventName} /from {eventStart} /to {eventEnd}` 

Adds an event task with the specified event name, start time and end time.

```
event added: [E][ ] erase all borders (from: 25 Dec 1995, to: 31 Dec 1995)
```

## Listing Tasks

`list`

Lists all tasks in the task list.

```
Here are the tasks in your list:
[T][ ] add new task
[D][ ] add new deadline (by: yesterday)
[E][ ] erase all borders (from: 25 Dec 1995, to: 31 Dec 1995)
```

## Marking Task as done

`mark {taskNumber}`

Marks the task with the specified task number as done.
```
Nice! I've marked this task as done:
[T][X] add new task
```

## Marking Task as undone

`unmark {taskNumber}`

Marks the task with the specified task number as undone.

## Deleting Tasks

`delete {taskNumber}`

Deletes the task with the specified task number.

```
OK, I've marked this task as not done yet:
[T][ ] add new task
```

## Searching Tasks

`find {keyword}`

Searches for tasks with the specified keyword.

```
Here are the matching tasks in your list:
[E][ ] erase all borders (from: 25 Dec 1995, to: 31 Dec 1995)
```
