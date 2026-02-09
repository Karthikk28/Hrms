package com.example.hrms.controller;

import com.example.hrms.entity.User;
import com.example.hrms.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }
}
