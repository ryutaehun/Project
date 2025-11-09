package com.shoppingmall.shoppingmall.controller;

import com.shoppingmall.shoppingmall.dto.TaskTagResponse;
import com.shoppingmall.shoppingmall.entity.MileStone;
import com.shoppingmall.shoppingmall.service.TaskInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projects/{id}/tasks/{taskId}")
public class TaskInfoController {

    private final TaskInfoService taskInfoService;

    @GetMapping("/tags")
    public ResponseEntity<List<TaskTagResponse>> getTaskTag(@PathVariable("id") Long projectId,
                                                            @PathVariable("taskId") Long taskId){
        List<TaskTagResponse> taskTags = taskInfoService.getTaskTags(projectId, taskId)
                .stream()
                .map(TaskTagResponse::from)
                .toList();

        return ResponseEntity.ok().body(taskTags);
    }

    @GetMapping("/milestone")
    public ResponseEntity<String> getTaskMileStone(@PathVariable("id") Long projectId,
                                                      @PathVariable("taskId") Long taskId){
        MileStone mileStone = taskInfoService.getTaskMileStone(projectId, taskId);
        return ResponseEntity.ok().body(mileStone.getName());
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Long>> getComments(@PathVariable("id") Long projectId,
                                                  @PathVariable("taskId") Long taskId){
        List<Long> comments = taskInfoService.getTaskComments(projectId, taskId);
        return ResponseEntity.ok().body(comments);
    }
}

