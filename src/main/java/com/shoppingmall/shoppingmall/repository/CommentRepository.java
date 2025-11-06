package com.shoppingmall.shoppingmall.repository;

import com.shoppingmall.shoppingmall.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
