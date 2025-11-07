package com.shoppingmall.shoppingmall.dto;

import com.shoppingmall.shoppingmall.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskResponse {
    private Long id;
    private String title;
    private String content;
//    private String milestoneTitle;
//    private List<String> tagNames;

    public static TaskResponse from(Task task) {
//        String milestoneTitle = task.getMileStone() != null ? task.getMileStone().getTitle() : null;
//        List<String> tagNames = task.getTaskTags().stream()
//                .map(taskTag -> taskTag.getTag().getName())
//                .toList();
//        return new TaskResponse(task.getId(), task.getTitle(), task.getContent(), milestoneTitle, tagNames);
        return new TaskResponse(task.getId(), task.getTitle(), task.getContent());
    }
}
