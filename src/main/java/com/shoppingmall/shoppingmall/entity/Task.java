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

    @Column(length = 55, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    /**
     * SSH_ID  : be12-team5
     * SSH_IP  : s2.java21.net
     * SSH_KEY : 7Z(rQe48hSvbYpdw
     * SSH_PORT : 8822
     */

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "milestone_id")
    private MileStone mileStone;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskTag> taskTags = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

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
