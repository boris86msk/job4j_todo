package ru.job4j.todo.repository;

import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TasksRepository {
    Optional<Task> save(Task task);
    List<Task> findAll();
    Optional<Task> findById(int id);
    List<Task> findByDone(boolean done);
    List<Priority> findAllPriority();
    boolean deleteById(int id);
    boolean update(Task task);

    boolean taskDone(int id);
}
