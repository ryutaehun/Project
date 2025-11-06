package com.shoppingmall.shoppingmall.controller;

import com.shoppingmall.shoppingmall.entity.Project;
import com.shoppingmall.shoppingmall.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProjectController{

    private final ProjectService projectService;

    @PostMapping("/projects")
    public Project create(@RequestHeader("memberId") Long memberId,
                          @RequestBody String name) {
        return projectService.create(name, memberId);
    }

    @GetMapping("/projects/{id}")
}
