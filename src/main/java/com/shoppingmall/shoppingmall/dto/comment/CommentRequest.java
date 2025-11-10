package com.shoppingmall.shoppingmall.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentRequest {
    @NotBlank(message = "댓글 내용을 작성해주세요.")
    @Size(max = 100, message = "댓글은 100자 이내로 작성해주세요.")
    private String commentContent;
}
