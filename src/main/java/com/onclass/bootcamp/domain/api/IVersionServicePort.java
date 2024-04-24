package com.onclass.bootcamp.domain.api;

import com.onclass.bootcamp.domain.model.Version;

import java.util.List;

public interface IVersionServicePort {
    void addVersion(Version version);
    List<Version> getAllBy(Integer page, Integer size, boolean isAscending, boolean orderByStartDate, boolean orderByMaxQuota);
    List<Version> getByBootcampId(Long bootcampId, Integer page, Integer size, boolean isAscending, boolean orderByStartDate, boolean orderByMaxQuota);
}