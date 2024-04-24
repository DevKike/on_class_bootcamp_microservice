package com.onclass.bootcamp.adapters.driven.jpa.mysql.mapper;

import com.onclass.bootcamp.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.onclass.bootcamp.domain.model.Version;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IVersionEntityMapper {
    @Mapping(target = "bootcamp", source = "bootcamp")
    VersionEntity toEntity(Version version);

    List<Version> toModelList(List<VersionEntity> versionEntities);
}