package com.shoppingmall.shoppingmall.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @Column(length = 50, nullable = false)
    private String projectName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private State state;

    @NotNull
    private Long adminId;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<MileStone> mileStones = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectMember> projectMembers = new ArrayList<>();

    public Project(String projectName, Long memberId) {
        this.projectName = projectName;
        state = State.ACTIVATE;
        this.adminId = memberId;
    }

    // 프로젝트에 멤버 추가 or 프로젝트 생성시 첫번째 멤버로 추가
    public void addProjectMember(ProjectMember member) {
        this.projectMembers.add(member);
        // adminMember 객체의 project 필드에 project 객체 추가
        member.setProject(this);
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
        for (Tag tag : tags) {
            tag.setProject(this);
        }
    }

    public void setMileStones(List<MileStone> mileStones){
        this.mileStones = mileStones;
        for(MileStone mileStone : mileStones){
            mileStone.setProject(this);
        }
    }
}
