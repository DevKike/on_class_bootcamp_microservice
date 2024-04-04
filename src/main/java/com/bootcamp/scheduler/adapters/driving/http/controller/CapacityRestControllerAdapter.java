package com.bootcamp.scheduler.adapters.driving.http.controller;

import com.bootcamp.scheduler.adapters.driving.http.dto.request.AddCapacityRequest;
import com.bootcamp.scheduler.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.bootcamp.scheduler.adapters.driving.http.mapper.ICapacityResponseMapper;
import com.bootcamp.scheduler.domain.api.ICapacityServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/capacity")
@RequiredArgsConstructor
public class CapacityRestControllerAdapter {
    private final ICapacityServicePort capacityServicePort;
    private final ICapacityRequestMapper capacityRequestMapper;
    private final ICapacityResponseMapper capacityResponseMapper;

    @PostMapping("/add")
    public ResponseEntity<Void> addCapacity(@RequestBody AddCapacityRequest request) {
        capacityServicePort.addCapacity(capacityRequestMapper.addRequestToCapacity(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/capacities/{capacityId}/technologies")
    public ResponseEntity<Void> addTechnologiesToCapacity(@PathVariable Long capacityId, @RequestBody Set<Long> technologyIds) {
        capacityServicePort.associateTechnologiesWithCapacity(capacityId, technologyIds);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}