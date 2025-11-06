package com.shoppingmall.shoppingmall.service.impl;

import com.shoppingmall.shoppingmall.entity.Comment;
import com.shoppingmall.shoppingmall.entity.Task;
import com.shoppingmall.shoppingmall.repository.CommentRepository;
import com.shoppingmall.shoppingmall.service.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public Comment create(long memberId, Task task, String content) {

        return commentRepository.save(new Comment(memberId, task, content));
    }

    @Override
    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }
}
