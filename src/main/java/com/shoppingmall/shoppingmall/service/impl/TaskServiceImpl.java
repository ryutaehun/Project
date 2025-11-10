package com.shoppingmall.shoppingmall.service.impl;

import com.shoppingmall.shoppingmall.dto.task.CreateTaskRequest;
import com.shoppingmall.shoppingmall.dto.task.UpdateTaskRequest;
import com.shoppingmall.shoppingmall.entity.*;
import com.shoppingmall.shoppingmall.exception.InvalidRequestException;
import com.shoppingmall.shoppingmall.exception.notfound.MileStoneNotFoundException;
import com.shoppingmall.shoppingmall.exception.notfound.ProjectNotFoundException;
import com.shoppingmall.shoppingmall.exception.notfound.TaskNotFoundException;
import com.shoppingmall.shoppingmall.repository.*;
import com.shoppingmall.shoppingmall.service.TaskService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final MileStoneRepository mileStoneRepository;
    private final TagRepository tagRepository;

    @Transactional
    @Override
    public Task create(long projectId, CreateTaskRequest request) {
        // 1. 프로젝트 유효성 검사
        Project project = projectRepository.findById(projectId);

        // 2. Task 생성 + project set
        Task task = new Task(request.getTaskTitle(), request.getTaskContent());
        task.setProject(project);

        // 3. 마일스톤 설정
        // 마일스톤 설정
        if (request.getMilestoneIdList() != null) {
            MileStone mileStone = mileStoneRepository.findByIdAndProjectId(request.getMilestoneIdList(), projectId);
            if (mileStone == null) {
                throw new MileStoneNotFoundException(request.getMilestoneIdList());
            }
            task.setMileStone(mileStone);
        }

        // 태그 설정
        if (request.getTagIdList() != null && !request.getTagIdList().isEmpty()) {
            List<Tag> tags = tagRepository.findAllByIdInAndProjectId(request.getTagIdList(), projectId);
            if (tags.size() != request.getTagIdList().size()) {
                throw new InvalidRequestException("ProjectId: " + projectId + "에 존재하지 않는 태그가 포함되어 있습니다.");
            }
            tags.forEach(task::addTaskTag);
        }


        // 4. 태그 설정
        List<Tag> tags = tagRepository.findAllByIdInAndProjectId(request.getTagIdList(), projectId);
        if(tags.size() != request.getTagIdList().size()){
            throw new InvalidRequestException("ProjectId: " + projectId + "에 존재하지 않는 태그가 포함되어 있습니다.");
        }
        tags.forEach(task::addTaskTag);

        return taskRepository.save(task);
    }

    // read : 프로젝트 내 전체 task 조회
    @Transactional(readOnly = true)
    @Override
    public List<Task> getTasksByProject(Long projectId){
        projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        return taskRepository.findAllByProject_Id(projectId);
    }

    // read : task 단건 조회
    @Transactional(readOnly = true)
    @Override
    public Task getTask(Long projectId, Long taskId) {
        projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        return taskRepository.findByIdAndProject_Id(taskId, projectId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    // delete
    @Transactional
    @Override
    public void deleteTask(Long projectId, Long taskId) {
        projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        Task task = getTask(projectId, taskId);
        taskRepository.delete(task); // CascadeType.ALL -> TaskTag도 자동 삭제
    }

    //update
    @Transactional
    @Override
    public void updateTask(Long projectId, Long taskId, UpdateTaskRequest request){
        projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        // 기존 task 조회
        Task task = getTask(projectId, taskId);

        // 수정
        task.setTitle(request.getTitle());
        task.setContent(request.getContent());

       if(request.getMilestoneIdList() != null){
           MileStone mileStone = mileStoneRepository.findByIdAndProjectId(request.getMilestoneIdList(),projectId);
           if(mileStone == null) {
               throw new MileStoneNotFoundException(request.getMilestoneIdList());
           }
           task.setMileStone(mileStone);
       }else {
           task.setMileStone(null);
       }

        task.getTaskTags().clear();
        if (!request.getTagIds().isEmpty()) {
            List<Tag> tags = tagRepository.findAllByIdInAndProjectId(request.getTagIds(), projectId);
            if(tags.size() != request.getTagIds().size()){
                throw new InvalidRequestException(
                        "ProjectId: " + projectId + "에 존재하지 않는 태그가 포함되어 있습니다."
                );
            }
            tags.forEach(task::addTaskTag);
        }
    }
}
