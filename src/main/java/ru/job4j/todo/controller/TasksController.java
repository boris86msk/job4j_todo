package ru.job4j.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Tasks;
import ru.job4j.todo.service.TasksService;

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
        model.addAttribute("tasks", tasksService.getFinishedTasks());
        return "index";
    }

    @GetMapping("/new")
    public String newList(Model model) {
        model.addAttribute("tasks", tasksService.getNewTasks());
        return "index";
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable int taskId) {
        tasksService.deleteTask(taskId);
        return "redirect:/index";
    }

    @GetMapping("/edite/{taskId}")
    public String editeTask(@PathVariable int taskId, Model model) {
        model.addAttribute("task", tasksService.getTaskById(taskId));
        return "edit_task";
    }

    @PostMapping("/edite")
    public String editeTask(@ModelAttribute Tasks tasks) {
        tasksService.editeTask(tasks);
        return "redirect:/index";
    }

    @PostMapping("/done")
    public String taskDone(@ModelAttribute Tasks tasks) {
        tasksService.editeTask(tasks);
        return "redirect:/index";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Tasks tasks, Model model) {
        try {
            tasksService.add(tasks);
            return "redirect:/index";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/task/{taskId}")
    public String getTaskPage(@PathVariable int taskId, Model model) {
        model.addAttribute("task", tasksService.getTaskById(taskId));
        return "task";
    }
}
