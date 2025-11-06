package com.shoppingmall.shoppingmall.repository;

import com.shoppingmall.shoppingmall.entity.Project;
import com.shoppingmall.shoppingmall.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByName(String name);
    Project getByName(String name);
    List<Project> getByState(State state);
    void deleteByName(String name);
}
