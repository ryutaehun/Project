package com.shoppingmall.shoppingmall.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// Tag가 뭔지 정확히 확인

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String category;

    @ManyToOne
    private Project project;

    @OneToMany(mappedBy = "tag")
    private List<TaskTag> taskTags = new ArrayList<>();

}
