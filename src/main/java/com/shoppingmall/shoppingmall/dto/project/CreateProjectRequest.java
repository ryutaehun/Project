package com.shoppingmall.shoppingmall.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectRequest {
    @NotBlank(message = "프로젝트명은 필수입니다.")
    @Size(max = 50, message = "프로젝트명은 50자 이내로 작성해야 합니다.")
    private String projectName;

    private List<String> tagList;
    private List<String> mileStoneList;
}
