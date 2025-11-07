package com.shoppingmall.shoppingmall.service;

import com.shoppingmall.shoppingmall.dto.CreateTaskRequest;
import com.shoppingmall.shoppingmall.entity.Task;

import java.util.List;

public interface TaskService {
    Task create(Long projectId, CreateTaskRequest createTaskRequest);
    List<Task> getTasksByProject(Long projectId);
    Task getTask(Long projectId, Long taskId);
    void deleteTask(Long projectId, Long taskId);
    //TaskCreationOptionsResponse getTasksByProject(Long projectId);

}
