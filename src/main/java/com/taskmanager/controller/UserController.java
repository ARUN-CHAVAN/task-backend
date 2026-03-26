package com.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taskmanager.entity.User;
import com.taskmanager.repository.UserRepository;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*") // allow React
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}