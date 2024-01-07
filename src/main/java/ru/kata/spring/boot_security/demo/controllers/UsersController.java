package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UserService service;

    @GetMapping("")
    public String user(Model model) {
        User user = service.getCurrentUser();
        Set<Role> userRoles = user.getRoles();
        model.addAttribute("sayname", user.getUsername());
        model.addAttribute("userRoles", userRoles);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/readonly")
    public String readonly(Model model, Principal principal) {
        model.addAttribute("saynamereadonly", principal.getName());
        return "readonly";
    }
}
