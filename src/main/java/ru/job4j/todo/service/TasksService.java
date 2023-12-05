package ru.job4j.todo.service;

import ru.job4j.todo.model.Tasks;

import java.util.List;

public interface TasksService {
    void add(Tasks tasks);
    List<Tasks> getAllTasks();
    List<Tasks> getFinishedTasks();
    List<Tasks> getNewTasks();
    Tasks getTaskById(int id);
    void deleteTask(int id);
    void editeTask(Tasks tasks);

}
