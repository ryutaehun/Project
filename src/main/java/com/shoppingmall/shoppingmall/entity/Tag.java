package com.shoppingmall.shoppingmall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// Tag가 뭔지 정확히 확인

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 28, nullable = false)
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.REMOVE)
    private List<TaskTag> taskTags = new ArrayList<>();

    public Tag(String name){
        this.name = name;
    }
}
