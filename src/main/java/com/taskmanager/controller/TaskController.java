package com.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.entity.Task;
import com.taskmanager.entity.User;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.repository.UserRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository repo;

    @Autowired
    private UserRepository userRepo; // ✅ added

    @PostMapping
    public Task create(@RequestBody Task task) {

        if (task.getAssignedTo() != null) {
            Long userId = task.getAssignedTo().getId();
            User user = userRepo.findById(userId).orElse(null);
            task.setAssignedTo(user);
        }

        return repo.save(task);
    }

    @GetMapping
    public List<Task> getAll() {
        return repo.findAll();
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return repo.save(task);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "Task Deleted";
    }
    
    @GetMapping("/user/{userId}")
    public List<Task> getTasksByUser(@PathVariable Long userId) {
        return repo.findByAssignedToId(userId);
    }
    
    @PutMapping("/{id}/status")
    public Task updateStatus(@PathVariable Long id, @RequestParam String status) {

        Task task = repo.findById(id).orElse(null);

        if (task != null) {
            task.setStatus(status);
            return repo.save(task);
        }

        return null;
    }
}