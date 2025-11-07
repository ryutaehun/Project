package com.shoppingmall.shoppingmall.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class TaskTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @Setter
    private Tag tag;

    @ManyToOne
    @Setter
    private Task task;
}

