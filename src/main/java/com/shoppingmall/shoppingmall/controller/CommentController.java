package com.shoppingmall.shoppingmall.controller;

import com.shoppingmall.shoppingmall.dto.comment.CommentRequest;
import com.shoppingmall.shoppingmall.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{projectId}/tasks/{taskId}/comments")
    public ResponseEntity<Long> register(@PathVariable Long projectId,
                                            @PathVariable Long taskId,
                                            @RequestHeader("memberId") Long memberId,
                                            @Valid @RequestBody CommentRequest createCommentRequest){
        commentService.create(memberId, taskId, createCommentRequest.getCommentContent());
        return ResponseEntity.ok().body(taskId);
    }

    @PutMapping("/{projectId}/tasks/{taskId}/comments/{commentId}")
    public ResponseEntity<Long> update(@PathVariable Long projectId,
                                       @PathVariable Long taskId,
                                       @PathVariable Long commentId,
                                       @RequestHeader("memberId") Long memberId,
                                       @Valid @RequestBody CommentRequest commentRequest
                                       ){
        commentService.update(memberId, taskId, commentId, commentRequest.getCommentContent());
        return ResponseEntity.ok().body(projectId);
    }

    @DeleteMapping("/{projectId}/tasks/{taskId}/comments/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long taskId,
                                       @PathVariable Long commentId,
                                       @RequestHeader("memberId") Long memberId,
                                       @PathVariable String projectId){
        commentService.delete(memberId, taskId, commentId);
        return ResponseEntity.noContent().build();
    }
}
