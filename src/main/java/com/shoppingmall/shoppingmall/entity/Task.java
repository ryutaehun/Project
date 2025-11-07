package com.shoppingmall.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String content;

    @ManyToOne
    private Project project;

//    @OneToMany
//    private List<MileStone> mileStones;

    @ManyToOne
    @JoinColumn(name = "milestone_id")
    private MileStone mileStone; // 하나의 Task는 하나의 Milestone에만 속함

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskTag> taskTags = new ArrayList<>();

    public Task(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addTaskTag(Tag tag) {
        TaskTag taskTag = new TaskTag();
        taskTag.setTask(this);
        taskTag.setTag(tag);
        this.taskTags.add(taskTag);
    }
}
