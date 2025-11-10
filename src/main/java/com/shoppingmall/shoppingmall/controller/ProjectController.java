package com.shoppingmall.shoppingmall.controller;

import com.shoppingmall.shoppingmall.dto.project.CreateProjectRequest;
import com.shoppingmall.shoppingmall.dto.project.CreateProjectResponse;
import com.shoppingmall.shoppingmall.dto.project.GetProjectResponse;
import com.shoppingmall.shoppingmall.dto.task.TaskListResponse;
import com.shoppingmall.shoppingmall.entity.Project;
import com.shoppingmall.shoppingmall.entity.ProjectMember;
import com.shoppingmall.shoppingmall.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectController{

    private final ProjectService projectService;

    // 프로젝트 이름과 멤버아이디(header로 들어옴, adminId에 memberId 삽입) 상태는 디폴트 값으로 ACTIVATE 부여
    @PostMapping
    public ResponseEntity<CreateProjectResponse> create(@RequestHeader("memberId") Long memberId,
                                                        @Valid @RequestBody CreateProjectRequest createProjectRequest) {
        Project project = projectService.create(createProjectRequest.getProjectName(), memberId, createProjectRequest.getTagList(), createProjectRequest.getMileStoneList());
        return ResponseEntity.ok().body(CreateProjectResponse.from(project));
    }

    // memberId에 해당하는 member가 가지고 있는 Project List를 보여줌
    @GetMapping("/by-member/{memberId}")
    public ResponseEntity<List<GetProjectResponse>> getProjects(@PathVariable("memberId") Long memberId){
        List<GetProjectResponse> responses = projectService.getProjectsByMemberId(memberId)
                .stream()
                .map(GetProjectResponse::from)
                .toList();
        return ResponseEntity.ok().body(responses);
    }

    // uri로 들어온 ProjectId에 해당하는 프로젝트에 memberId를 저장함
    @PostMapping("/{projectId}/members")
    public ResponseEntity<ProjectMember> createProjectMember(@PathVariable("projectId") Long projectId,
                                                             @RequestBody Long memberId){
        projectService.addMemberToProject(projectId, memberId);

        return ResponseEntity.ok().build();
    }
}
