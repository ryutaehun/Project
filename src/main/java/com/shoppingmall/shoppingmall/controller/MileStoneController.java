package com.shoppingmall.shoppingmall.controller;

import com.shoppingmall.shoppingmall.dto.mileStone.CreateMileStoneRequest;
import com.shoppingmall.shoppingmall.dto.mileStone.GetMileStoneResponse;
import com.shoppingmall.shoppingmall.dto.mileStone.MileStoneRequest;
import com.shoppingmall.shoppingmall.entity.MileStone;
import com.shoppingmall.shoppingmall.service.MileStoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class MileStoneController {

    private final MileStoneService mileStoneService;

    // mileStone을 추가하는 컨트롤러
    @PostMapping("/{projectId}/milestones")
    public ResponseEntity<Long> create(@PathVariable long projectId,
                                                          @Valid @RequestBody MileStoneRequest createMileStoneRequest,
                                                          @RequestHeader("memberId") Long memberId){
        mileStoneService.create(projectId, memberId, createMileStoneRequest);
        return ResponseEntity.ok().body(projectId);
    }

    // 해당 프로젝트의 mileStone 목록을 보여주는 컨트롤러
    @GetMapping("/{projectId}/milestones")
    public ResponseEntity<List<GetMileStoneResponse>> getMileStones(@PathVariable long projectId){
        List<GetMileStoneResponse> responses = mileStoneService.getMileStones(projectId)
                .stream()
                .map(GetMileStoneResponse::from)
                .toList();

        return ResponseEntity.ok().body(responses);
    }

    @PutMapping("/{projectId}/milestones/{milestoneId}")
    public ResponseEntity<Long> update(@PathVariable long projectId,
                            @PathVariable long milestoneId,
                            @RequestBody MileStoneRequest updatedMileStone) {
        mileStoneService.update(projectId, milestoneId, updatedMileStone);
        return ResponseEntity.ok().body(projectId);
    }

    @DeleteMapping("/{projectId}/milestones/{milestoneId}")
    public ResponseEntity<Void> delete(@PathVariable long projectId,
                       @PathVariable long milestoneId) {
        mileStoneService.delete(projectId, milestoneId);
        return ResponseEntity.noContent().build();
    }
}
