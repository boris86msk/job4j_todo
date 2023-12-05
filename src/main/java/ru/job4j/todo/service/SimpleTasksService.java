package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Tasks;
import ru.job4j.todo.repository.TasksRepository;

import java.util.List;

@Service
public class SimpleTasksService implements TasksService {
    private final TasksRepository tasksRepository;

    public SimpleTasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    public void add(Tasks tasks) {
       tasksRepository.save(tasks);
    }

    @Override
    public List<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }

    @Override
    public List<Tasks> getFinishedTasks() {
        return tasksRepository.findFinished();
    }

    @Override
    public List<Tasks> getNewTasks() {
        return tasksRepository.findNew();
    }

    @Override
    public Tasks getTaskById(int id) {
        return tasksRepository.findById(id);
    }

    @Override
    public void deleteTask(int id) {
        tasksRepository.deleteById(id);
    }

    @Override
    public void editeTask(Tasks tasks) {
        tasksRepository.update(tasks);
    }
}
