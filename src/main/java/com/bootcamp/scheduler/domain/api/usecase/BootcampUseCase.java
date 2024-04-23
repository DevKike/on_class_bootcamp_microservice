package com.bootcamp.scheduler.domain.api.usecase;

import com.bootcamp.scheduler.domain.api.IBootcampServicePort;
import com.bootcamp.scheduler.domain.model.Bootcamp;
import com.bootcamp.scheduler.domain.spi.IBootcampPersistencePort;

import java.util.List;
import java.util.Set;

public class BootcampUseCase implements IBootcampServicePort {
    private final IBootcampPersistencePort bootcampPersistencePort;

    public BootcampUseCase(IBootcampPersistencePort bootcampPersistencePort) {
        this.bootcampPersistencePort = bootcampPersistencePort;
    }

    @Override
    public void addBootcamp(Bootcamp bootcamp) {
        bootcampPersistencePort.addBootcamp(bootcamp);
    }

    @Override
    public void associateCapacitiesWithBootcamp(Long bootcampId, Set<Long> capacitiesIds) {
        bootcampPersistencePort.associateCapacitiesWithBootcamp(bootcampId, capacitiesIds);
    }

    @Override
    public List<Bootcamp> getAllBootcamps(Integer page, Integer size, boolean isAscending, boolean orderByCapCount) {
        return bootcampPersistencePort.getAllBootcamps(page, size, isAscending, orderByCapCount);
    }
}