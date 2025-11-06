package com.shoppingmall.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    private long memberId;

    @ManyToOne
    private Task task;

    public Comment(long memberId, Task task, String content) {
        this.memberId = memberId;
        this.task = task;
        this.content = content;
    }
}
