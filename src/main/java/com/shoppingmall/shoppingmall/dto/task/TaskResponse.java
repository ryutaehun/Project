package com.shoppingmall.shoppingmall.dto.task;

import com.shoppingmall.shoppingmall.entity.Task;

public record TaskResponse(Long projectId, String projectName, String taskTitle, String taskContent){
    public static TaskResponse from(Task task){
        return new TaskResponse(
                task.getProject().getId(),
                task.getProject().getProjectName(),
                task.getTitle(),
                task.getContent()
        );
    }
}