package com.onclass.bootcamp.adapters.driving.http.mapper;

import com.onclass.bootcamp.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.onclass.bootcamp.adapters.driving.http.dto.response.TechnologyResponse;
import com.onclass.bootcamp.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITechnologyRequestMapper {
    @Mapping(target = "id", ignore = true)
    Technology addRequestToTechnology(AddTechnologyRequest addTechnologyRequest);
    List<TechnologyResponse> toTechnologyResponseList(List<Technology> technologies);
}