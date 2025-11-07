package com.shoppingmall.shoppingmall.repository;

import com.shoppingmall.shoppingmall.entity.MileStone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MileStoneRepository extends JpaRepository<MileStone, Long> {
    List<MileStone> findByProject_Id(Long projectId);
    boolean existsByName(String name);
}
