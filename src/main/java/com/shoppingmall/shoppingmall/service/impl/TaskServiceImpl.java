package com.shoppingmall.shoppingmall.service.impl;

import com.shoppingmall.shoppingmall.dto.CreateTaskRequest;
import com.shoppingmall.shoppingmall.entity.*;
import com.shoppingmall.shoppingmall.repository.*;
import com.shoppingmall.shoppingmall.service.TaskService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final MileStoneRepository mileStoneRepository;
    private final TagRepository tagRepository;
    private final TaskTagRepository taskTagRepository;

    @Transactional
    @Override
    public Task create(Long projectId, CreateTaskRequest request) {
        Project project = getProject(projectId);
        Task task = createTaskEntity(request, project);

        setMilestoneIfExists(task, request.getMilestoneId());
        setTagsIfExists(task, request.getTagIds());

        return taskRepository.save(task);
    }

    // read : 프로젝트 내 전체 task 조회
    @Transactional(readOnly = true)
    @Override
    public List<Task> getTasksByProject(Long projectId){
        getProject(projectId);

        return taskRepository.findByProject_Id(projectId);
    }

    // read : task 단건 조회
    @Transactional(readOnly = true)
    @Override
    public Task getTask(Long projectId, Long taskId) {
        return taskRepository.findByIdAndProject_Id(taskId, projectId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found for projectId=" + projectId + ", taskId=" + taskId));
    }

    // delete
    @Transactional
    @Override
    public void deleteTask(Long projectId, Long taskId) {
        Task task = getTask(projectId, taskId); // 위 메서드 재사용
        taskTagRepository.deleteAll(taskTagRepository.findByTask(task));
        taskRepository.delete(task);
    }

    // == 메서드 분리 == //
    private Project getProject(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found: " + projectId));
    }

    private Task createTaskEntity(CreateTaskRequest request, Project project) {
        Task task = new Task(request.getTitle(), request.getContent());
        task.setProject(project);
        return task;
    }

    private void setMilestoneIfExists(Task task, Long milestoneId) {
        if (milestoneId == null) return;
        MileStone milestone = mileStoneRepository.findById(milestoneId)
                .orElseThrow(() -> new IllegalArgumentException("Milestone not found: " + milestoneId));
        task.setMileStone(milestone);

    }

    private void setTagsIfExists(Task task, List<Long> tagIds) {
        if (tagIds == null || tagIds.isEmpty()) return;
        List<Tag> tags = tagRepository.findAllById(tagIds);
        tags.forEach(task::addTaskTag);
    }

    /*
    milestone, tag 리스트 반환 -> gateway에서 처리가능. 필요없는 코드
    @Transactional(readOnly = true)
    @Override
    public TaskCreationOptionsResponse getTasksByProject(Long projectId) {
        List<MileStoneResponse> mileStones = mileStoneRepository.findByProject_Id(projectId)
                .stream()
                .map(MileStoneResponse::from)
                .toList();

        List<TagResponse> tags = tagRepository.findByProject_Id(projectId)
                .stream()
                .map(TagResponse::from)
                .toList();

        return new TaskCreationOptionsResponse(mileStones, tags);
    }
     */



}
