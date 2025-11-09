package com.shoppingmall.shoppingmall.service;

import com.shoppingmall.shoppingmall.entity.MileStone;
import com.shoppingmall.shoppingmall.entity.TaskTag;

import java.util.List;

public interface TaskInfoService {
    List<TaskTag> getTaskTags(Long projectId, Long taskId);
    MileStone getTaskMileStone(Long projectId, Long taskId);
    List<Long> getTaskComments(Long projectId, Long taskId);
}
