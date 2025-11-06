package com.shoppingmall.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String content;

    @OneToMany(mappedBy = "task")
    private List<TaskTag> taskTags = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private MileStone mileStone;
}
