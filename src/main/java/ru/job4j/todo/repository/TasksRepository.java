package ru.job4j.todo.repository;

import ru.job4j.todo.model.Tasks;

import java.util.List;

public interface TasksRepository {
    void save(Tasks tasks);
    List<Tasks> findAll();
    Tasks findById(int id);
    List<Tasks> findNew();
    List<Tasks> findFinished();
    void deleteById(int id);
    void update(Tasks tasks);
}
