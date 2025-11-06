package com.shoppingmall.shoppingmall.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class MileStone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private Project project;

    @OneToMany
    private List<Task> tasks = new ArrayList<>();
}
