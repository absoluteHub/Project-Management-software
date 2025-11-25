package org.example.projectmanagementsoftware.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.domain.User;
import org.example.projectmanagementsoftware.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users";
    }
}
