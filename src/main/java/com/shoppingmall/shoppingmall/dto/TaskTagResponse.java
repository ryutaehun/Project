package com.shoppingmall.shoppingmall.dto;

import com.shoppingmall.shoppingmall.entity.TaskTag;

public record TaskTagResponse (String name){
    public static TaskTagResponse from(TaskTag taskTag){
        return new TaskTagResponse(taskTag.getTag().getName());
    }
}
