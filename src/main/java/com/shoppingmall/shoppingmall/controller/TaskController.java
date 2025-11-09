package com.shoppingmall.shoppingmall.controller;

import com.shoppingmall.shoppingmall.dto.task.CreateTaskRequest;
import com.shoppingmall.shoppingmall.dto.task.TaskListResponse;
import com.shoppingmall.shoppingmall.dto.task.TaskResponse;
import com.shoppingmall.shoppingmall.dto.task.UpdateTaskRequest;
import com.shoppingmall.shoppingmall.entity.Task;
import com.shoppingmall.shoppingmall.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/projects")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/{id}/tasks")
    public ResponseEntity<TaskResponse> create(@PathVariable("id") Long projectId,
                                               @Valid @RequestBody CreateTaskRequest createTaskRequest){
        Task task = taskService.create(projectId, createTaskRequest);
        return ResponseEntity.ok().body(TaskResponse.from(task));
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskListResponse>> getTasksByProject(@PathVariable("id") Long projectId){
        List<TaskListResponse> responses = taskService.getTasksByProject(projectId)
                .stream()
                .map(TaskListResponse::from)
                .toList();
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/{id}/tasks/{taskId}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable("id") Long projectId,
                                                @PathVariable("taskId") Long taskId) {
        Task task = taskService.getTask(projectId,taskId);
        return ResponseEntity.ok().body(TaskResponse.from(task));
    }

    @PutMapping("/{id}/tasks/{taskId}")
    public ResponseEntity<Long> updateTask(@PathVariable("id") Long projectId,
                                           @PathVariable("taskId") Long taskId,
                                           @Valid @RequestBody UpdateTaskRequest updateTaskRequest){
        taskService.updateTask(projectId, taskId, updateTaskRequest);
        return ResponseEntity.ok().body(taskId);
    }

    @DeleteMapping("/{id}/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long projectId,
                                           @PathVariable("taskId") Long taskId) {
        taskService.deleteTask(projectId,taskId);
        return ResponseEntity.noContent().build();
    }
}
