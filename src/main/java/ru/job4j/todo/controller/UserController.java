package ru.job4j.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.model.UserStore;
import ru.job4j.todo.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "users/login";
    }

    @GetMapping("/register")
    public String getRegistrationPage() {
        return "users/register";
    }

    @PostMapping("/register")
    public String registration(Model model, @ModelAttribute UserStore user, HttpServletRequest request) {
        var savedUser = userService.saveUser(user);
        if (savedUser.isEmpty()) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "errors/error";
        }
        //return loginUser(user, model, request);
        return "";
    }
}
