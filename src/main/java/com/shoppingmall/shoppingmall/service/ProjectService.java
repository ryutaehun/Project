package com.shoppingmall.shoppingmall.service;

import com.shoppingmall.shoppingmall.entity.Project;
import com.shoppingmall.shoppingmall.entity.State;

import java.util.List;

public interface ProjectService {
    Project create(String name, Long memberId);
    Project getByName(String name);
    List<Project> getByState(State state);
    void deleteByName(String name);
//    void updateStateByName(String name, State state);
}

// 프로젝트 상태를 만들 때 설정을 해야하나?