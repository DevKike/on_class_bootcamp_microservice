package com.onclass.bootcamp.adapters.driving.http.controller;

import com.onclass.bootcamp.adapters.driving.http.dto.request.AddCapacityRequest;
import com.onclass.bootcamp.adapters.driving.http.dto.response.CapacityResponse;
import com.onclass.bootcamp.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.onclass.bootcamp.adapters.driving.http.mapper.ICapacityResponseMapper;
import com.onclass.bootcamp.domain.api.ICapacityServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/{capacityId}/add/technologies")
    public ResponseEntity<Void> addTechnologiesToCapacity(@PathVariable Long capacityId, @RequestBody Set<Long> technologyIds) {
        capacityServicePort.associateTechnologiesWithCapacity(capacityId, technologyIds);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getAllCapacities")
    public ResponseEntity<List<CapacityResponse>> getAllCapacities(@RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false, defaultValue = "true") boolean isAscending, @RequestParam(required = false, defaultValue = "false") boolean orderByTechCount) {
        return ResponseEntity.ok(capacityResponseMapper.toCapacityResponseList(capacityServicePort.getAllCapacities(page, size, isAscending, orderByTechCount)));
    }
}