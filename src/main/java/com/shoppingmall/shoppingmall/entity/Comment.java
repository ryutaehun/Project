package com.shoppingmall.shoppingmall.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, nullable = false)
    private String content;

    @NotNull
    private long memberId;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Comment(long memberId, Task task, String content) {
        this.memberId = memberId;
        this.task = task;
        this.content = content;
    }
}
