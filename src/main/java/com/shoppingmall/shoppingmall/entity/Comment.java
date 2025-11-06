package com.shoppingmall.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    private long memberId;

    @ManyToOne
    private Task task;
}
