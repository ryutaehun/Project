package com.shoppingmall.shoppingmall.entity;


import jakarta.persistence.*;

@Entity
public class TaskTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Tag tag;

    @ManyToOne
    private Task task;
}
