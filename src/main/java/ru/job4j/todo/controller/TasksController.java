package ru.job4j.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TasksService;

import java.util.Optional;

@Controller
public class TasksController {
    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping("index")
    public String getIndexPage(Model model) {
        model.addAttribute("tasks", tasksService.getAllTasks());
        return "index";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "create_task";
    }

    @GetMapping("/full")
    public String fullList(Model model) {
        model.addAttribute("tasks", tasksService.getAllTasks());
        return "index";
    }

    @GetMapping("/finished")
    public String finishedList(Model model) {
        model.addAttribute("tasks", tasksService.getTasksByDone(true));
        return "index";
    }

    @GetMapping("/new")
    public String newList(Model model) {
        model.addAttribute("tasks", tasksService.getTasksByDone(false));
        return "index";
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable int taskId, Model model) {
        if (tasksService.deleteTask(taskId)) {
            return "redirect:/index";
        } else {
            model.addAttribute("message", "Неудалось удалить задачу");
            return "errors/error";
        }
    }

    @GetMapping("/edite/{taskId}")
    public String editeTask(@PathVariable int taskId, Model model) {
        Optional<Task> taskById = tasksService.getTaskById(taskId);
        if (taskById.isPresent()) {
            model.addAttribute("task", taskById.get());
            return "edit_task";
        } else {
            model.addAttribute("message", "Неудалось обновить задачу");
            return "errors/error";
        }
    }

    @PostMapping("/edite")
    public String editeTask(@ModelAttribute Task task, Model model) {
        if (tasksService.editeTask(task)) {
            return "redirect:/index";
        } else {
            model.addAttribute("message", "Неудалось редактировать задачу");
            return "errors/error";
        }
    }

    @PostMapping("/done")
    public String taskDone(@ModelAttribute Task task, Model model) {
        if (tasksService.taskDone(task.getId())) {
            return "redirect:/index";
        } else {
            model.addAttribute("message", "Неудалось закрыть задачу");
            return "errors/error";
        }
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task, Model model) {
        if (tasksService.add(task).isPresent()) {
            return "redirect:/index";
        } else {
            model.addAttribute("message", "Неудалось добавить задачу");
            return "errors/error";
        }
    }

    @GetMapping("/task/{taskId}")
    public String getTaskPage(@PathVariable int taskId, Model model) {
        Optional<Task> taskById = tasksService.getTaskById(taskId);
        if (taskById.isPresent()) {
            model.addAttribute("task", taskById.get());
            return "task";
        } else {
            model.addAttribute("message", "Неудалось добавить задачу");
            return "errors/error";
        }
    }
}
