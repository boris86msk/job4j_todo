package ru.job4j.todo.service;

import ru.job4j.todo.model.Priority;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TasksService {
    Optional<Task> add(Task task);
    List<Task> getAllTasks();
    List<Task> getTasksByDone(boolean done);
    List<Priority> getAllPriority();
    Optional<Task> getTaskById(int id);
    boolean deleteTask(int id);
    boolean editeTask(Task task);
    boolean taskDone(int id);

}
