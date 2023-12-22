package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.CategoryRepository;
import ru.job4j.todo.repository.TasksRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SimpleTasksService implements TasksService {
    private final TasksRepository tasksRepository;
    private final CategoryRepository categoryRepository;

    public SimpleTasksService(TasksRepository tasksRepository, CategoryRepository categoryRepository) {
        this.tasksRepository = tasksRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Task> add(Task task) {
        return tasksRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return tasksRepository.findAll();
    }

    @Override
    public List<Task> getTasksByDone(boolean done) {
        return tasksRepository.findByDone(done);
    }

    @Override
    public List<Priority> getAllPriority() {
        return tasksRepository.findAllPriority();
    }

    @Override
    public Set<Category> getAllCategory() {
        return categoryRepository.findAllCategory();
    }

    @Override
    public Set<Category> getCategoryById(List<Integer> listInt) {
        return categoryRepository.findCategoryById(listInt);
    }

    @Override
    public Optional<Task> getTaskById(int id) {
        return tasksRepository.findById(id);
    }

    @Override
    public boolean deleteTask(int id) {
        return tasksRepository.deleteById(id);
    }

    @Override
    public boolean editeTask(Task task) {
        return tasksRepository.update(task);
    }

    @Override
    public boolean taskDone(int id) {
        return tasksRepository.taskDone(id);
    }
}
