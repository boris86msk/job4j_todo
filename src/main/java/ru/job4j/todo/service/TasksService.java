package ru.job4j.todo.service;

import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TasksService {
    Optional<Task> add(Task task);
    List<Task> getAllTasks();
    List<Task> getTasksByDone(boolean done);
    List<Priority> getAllPriority();
    Set<Category> getAllCategory();
    Set<Category> getCategoryById(List<Integer> listInt);
    Optional<Task> getTaskById(int id);
    boolean deleteTask(int id);
    boolean editeTask(Task task);
    boolean taskDone(int id);


}
