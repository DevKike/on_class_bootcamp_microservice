package com.bootcamp.scheduler.domain.model;

import com.bootcamp.scheduler.domain.exception.EmptyFieldException;
import com.bootcamp.scheduler.domain.util.DomainConstants;

import static java.util.Objects.requireNonNull;

public class Technology {
    private final Long id;
    private final String name;
    private String description;

    public Technology(Long id, String name, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
        if (description == null || description.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        }

        this.id = id;
        this.name = requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}