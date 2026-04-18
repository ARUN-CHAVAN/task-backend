package com.taskmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}