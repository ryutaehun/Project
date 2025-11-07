package com.shoppingmall.shoppingmall.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateTaskRequest {
    private String title;
    private String content;
    private Long milestoneId; // 마일스톤 한개
    private List<Long> tagIds; // 여러개의 태그
}
