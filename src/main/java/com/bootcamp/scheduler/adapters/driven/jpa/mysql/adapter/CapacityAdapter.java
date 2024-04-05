package com.bootcamp.scheduler.adapters.driven.jpa.mysql.adapter;

import com.bootcamp.scheduler.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.bootcamp.scheduler.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.bootcamp.scheduler.adapters.driven.jpa.mysql.exception.AlreadyExistsException;
import com.bootcamp.scheduler.adapters.driven.jpa.mysql.exception.NotFoundException;
import com.bootcamp.scheduler.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.bootcamp.scheduler.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.bootcamp.scheduler.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.bootcamp.scheduler.domain.exception.SizeException;
import com.bootcamp.scheduler.domain.model.Capacity;
import com.bootcamp.scheduler.domain.spi.ICapacityPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class CapacityAdapter implements ICapacityPersistencePort {
    private final ICapacityRepository capacityRepository;
    private final ITechnologyRepository technologyRepository;
    private final ICapacityEntityMapper capacityEntityMapper;

    @Override
    public void addCapacity(Capacity capacity) {
        if (capacityRepository.findByName(capacity.getName()).isPresent()) {
            throw new AlreadyExistsException("Capacity already exists");
        }

        capacityRepository.save(capacityEntityMapper.toEntity(capacity));
    }

    @Override
    public void associateTechnologiesWithCapacity(Long capacityId, Set<Long> technologyIds) {
        Optional<CapacityEntity> capacityOptional = capacityRepository.findById(capacityId);

        if (capacityOptional.isEmpty()) {
            throw new NotFoundException("Capacity was not found");
        }

        CapacityEntity capacityEntity = capacityOptional.get();

        if (capacityEntity.getTechnologies().size() + technologyIds.size() < 3) {
            throw new SizeException("Capacity must have at least 3 associated technologies");
        }

        if (capacityEntity.getTechnologies().size() + technologyIds.size() > 20) {
            throw new SizeException("Capacity can have maximum 20 associated technologies");
        }

        for (Long technologyId : technologyIds) {
            for (TechnologyEntity technology : capacityEntity.getTechnologies()) {
                if (technology.getId().equals(technologyId)) {
                    throw new AlreadyExistsException("Capacity already has technology with id: " + technologyId);
                }
            }
        }

        Set<TechnologyEntity> newTechnologies = new HashSet<>();
        for (Long technologyId : technologyIds) {
            Optional<TechnologyEntity> technologyOptional = technologyRepository.findById(technologyId);
            if (technologyOptional.isPresent()) {
                newTechnologies.add(technologyOptional.get());
            } else {
                throw new NotFoundException("Technology with id " + technologyId + " not found");
            }
        }

        capacityEntity.getTechnologies().addAll(newTechnologies);
        capacityRepository.save(capacityEntity);
    }
}