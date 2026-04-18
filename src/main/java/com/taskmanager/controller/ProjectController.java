package com.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.entity.Project;
import com.taskmanager.entity.User;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.repository.UserRepository;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository repo;
    @Autowired
    private UserRepository userRepo;

    @PostMapping
    public Project create(@RequestBody Project project) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepo.findByEmail(email);

        project.setUser(user); 

        return repo.save(project);
    }
    
    @GetMapping
    public List<Project> getAll() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return repo.findByUserEmail(email);
    }
    
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        repo.deleteById(id);
    }
}