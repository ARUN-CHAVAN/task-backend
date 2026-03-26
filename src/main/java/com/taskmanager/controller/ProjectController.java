package com.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.entity.Project;
import com.taskmanager.repository.ProjectRepository;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository repo;

    @PostMapping
    public Project create(@RequestBody Project project) {
        return repo.save(project);
    }

    @GetMapping
    public List<Project> getAll() {
        return repo.findAll();
    }
    
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        repo.deleteById(id);
    }
}