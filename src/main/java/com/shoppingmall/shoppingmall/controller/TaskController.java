package com.shoppingmall.shoppingmall.controller;

import com.shoppingmall.shoppingmall.dto.CreateTaskRequest;
import com.shoppingmall.shoppingmall.dto.TaskResponse;
import com.shoppingmall.shoppingmall.entity.Task;
import com.shoppingmall.shoppingmall.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/{id}/tasks")
    public ResponseEntity<TaskResponse> create(@PathVariable("id") Long projectId,
                                               @RequestBody CreateTaskRequest createTaskRequest){
        Task task = taskService.create(projectId, createTaskRequest);
        return ResponseEntity.ok(TaskResponse.from(task));
    }

    // TODO TaskResponse에 마일스톤, 태그도 추가필요??
    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskResponse>> getTasksByProject(@PathVariable("id") Long projectId){
        List<TaskResponse> responses = taskService.getTasksByProject(projectId)
                .stream()
                .map(TaskResponse::from)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}/tasks/{taskId}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable("id") Long projectId,
                                                @PathVariable("taskId") Long taskId) {
        Task task = taskService.getTask(projectId,taskId);
        return ResponseEntity.ok(TaskResponse.from(task));
    }

    @DeleteMapping("/{id}/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long projectId,
                                           @PathVariable("taskId") Long taskId) {
        taskService.deleteTask(projectId,taskId);
        return ResponseEntity.noContent().build();
    }
}
