package com.taskmanager.service;

import java.util.List;
import com.taskmanager.entity.Task;

public interface TaskService {
    List<Task> getTasksForLoggedInUser();
}
