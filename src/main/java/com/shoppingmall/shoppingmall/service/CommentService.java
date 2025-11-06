package com.shoppingmall.shoppingmall.service;

import com.shoppingmall.shoppingmall.entity.Comment;
import com.shoppingmall.shoppingmall.entity.Task;

public interface CommentService {
    Comment create(long memberId, Task task, String content);
    void deleteComment(long commentId);
}
