package com.shoppingmall.shoppingmall.service.impl;

import com.shoppingmall.shoppingmall.entity.Comment;
import com.shoppingmall.shoppingmall.entity.MileStone;
import com.shoppingmall.shoppingmall.entity.Task;
import com.shoppingmall.shoppingmall.entity.TaskTag;
import com.shoppingmall.shoppingmall.exception.notfound.TaskNotFoundException;
import com.shoppingmall.shoppingmall.repository.CommentRepository;
import com.shoppingmall.shoppingmall.repository.TaskRepository;
import com.shoppingmall.shoppingmall.repository.TaskTagRepository;
import com.shoppingmall.shoppingmall.service.TaskInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskInfoServiceImpl implements TaskInfoService {

    private final TaskRepository taskRepository;
    private final TaskTagRepository taskTagRepository;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    @Override
    public List<TaskTag> getTaskTags(Long projectId, Long taskId) {
        return taskTagRepository.findAllByTaskId(taskId);
    }

    @Transactional(readOnly = true)
    @Override
    public MileStone getTaskMileStone(Long projectId, long taskId) {
        Task task = taskRepository.findById(taskId);

        return task.getMileStone();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Long> getTaskComments(Long projectId, Long taskId) {
        List<Comment> comments = commentRepository.findAllByTaskId(taskId);
        return comments
                .stream()
                .map(Comment::getId)
                .toList();
    }
}
