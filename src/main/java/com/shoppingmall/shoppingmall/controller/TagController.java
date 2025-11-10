package com.shoppingmall.shoppingmall.controller;

import com.shoppingmall.shoppingmall.dto.tag.CreateTagRequest;
import com.shoppingmall.shoppingmall.dto.tag.GetTagResponse;
import com.shoppingmall.shoppingmall.entity.Tag;
import com.shoppingmall.shoppingmall.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class TagController {
    private final TagService tagService;

    @PostMapping("/{projectId}/tags")
    public ResponseEntity<Long> create(@PathVariable("projectId") Long projectId,
                                              @Valid @RequestBody CreateTagRequest createTagRequest){
        tagService.create(projectId, createTagRequest);
        return ResponseEntity.ok().body(projectId);
    }

    @GetMapping("/{projectId}/tags")
    public ResponseEntity<List<GetTagResponse>> getTags(@PathVariable("projectId") Long projectId) {
        List<GetTagResponse> responses = tagService.getTags(projectId)
                .stream()
                .map(GetTagResponse::from)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{projectId}/tags/{tagId}")
    public ResponseEntity<Long> update(@PathVariable long projectId,
                           @PathVariable long tagId,
                           @Valid @RequestBody CreateTagRequest updatedTag) {
        tagService.updateTag(projectId, tagId, updatedTag);

        return ResponseEntity.ok().body(projectId);
    }

    @DeleteMapping("/{projectId}/tags/{tagId}")
    public ResponseEntity<Void> delete(@PathVariable("projectId") Long projectId,
                                       @PathVariable("tagId") Long tagId){
        tagService.deleteTag(projectId, tagId);
        return ResponseEntity.noContent().build();
    }
}
