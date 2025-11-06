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

    @ManyToOne
    private Project project;

    @ManyToOne
    private MileStone mileStone;

    @OneToMany(mappedBy = "task")
    private List<TaskTag> taskTags = new ArrayList<>();

}
