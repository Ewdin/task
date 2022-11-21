package com.task.task.controllers;

import com.task.task.entities.Task;
import com.task.task.entities.User;
import com.task.task.services.TaskService;
import com.task.task.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

public class FrontControllers {

    TaskService service;
    UserService userService;

    public FrontControllers(TaskService service, UserService userService){
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/")

    public String index (Model model, @AuthenticationPrincipal OidcUser principal){
        if (principal != null) {
           User user = this.userService.getOrCreateUser(principal.getClaims());
            model.addAttribute("user", user);

        }

        return "index";
    }

    @GetMapping("/tasks")
    public String tasks(Model model){
        List<Task> tasks = this.service.getTaskList();
        model.addAttribute("tasks",tasks);
        return "tasks";
    }

    @GetMapping("/tasks/new")
    public String newTask(Model model){
        model.addAttribute("task", new Task());
        return "new-task";
    }

}
