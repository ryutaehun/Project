package com.shoppingmall.shoppingmall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@NoArgsConstructor
@Setter
public class ProjectMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @NotNull
    private long memberId;

    public ProjectMember(long memberId){
        this.memberId = memberId;
    }
}
