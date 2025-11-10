package com.shoppingmall.shoppingmall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class MileStone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 20, nullable = false)
    private String name;

    @OneToMany(mappedBy = "mileStone", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public MileStone(String name){
        this.name = name;
    }

}
