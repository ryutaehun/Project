package com.shoppingmall.shoppingmall.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTaskRequest {
    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 50, message = "제목은 50자 이내로 작성해야 합니다.")
    private String taskTitle;

    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 1000, message = "내용은 1000자 이내로 작성해주세요.")
    private String taskContent;

    private Long milestoneIdList;
    private List<Long> tagIdList = new ArrayList<>();
}
