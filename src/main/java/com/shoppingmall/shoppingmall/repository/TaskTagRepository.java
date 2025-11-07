package com.shoppingmall.shoppingmall.repository;

import com.shoppingmall.shoppingmall.entity.Task;
import com.shoppingmall.shoppingmall.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskTagRepository extends JpaRepository<TaskTag, Long> {
    List<TaskTag> findByTask(Task task);
}
