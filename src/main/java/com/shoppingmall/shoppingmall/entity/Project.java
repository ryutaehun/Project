package com.shoppingmall.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private State state;

    private Long adminId;

    @OneToMany(mappedBy = "project")
    private List<ProjectMember> projectMember = new ArrayList<>();

    public Project(String name, Long memberId){
        this.name = name;
        state = State.ACTIVATE;
        this.adminId = memberId;
    }
}
