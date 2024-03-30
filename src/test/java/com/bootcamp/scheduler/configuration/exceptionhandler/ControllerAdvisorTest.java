package com.bootcamp.scheduler.configuration.exceptionhandler;

import com.bootcamp.scheduler.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.bootcamp.scheduler.configuration.Constants;
import com.bootcamp.scheduler.domain.exception.EmptyFieldException;
import com.bootcamp.scheduler.domain.exception.MaxSizeExceededException;
import com.bootcamp.scheduler.testdata.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ControllerAdvisorTest {

    private final ControllerAdvisor controllerAdvisor = new ControllerAdvisor();

    @Test
    void itShouldHandleEmptyFieldExceptionSuccessfully() {
        EmptyFieldException exception = TestData.getEmptyFieldException();

        ResponseEntity<ExceptionResponse> responseEntity = controllerAdvisor.handleEmptyFieldException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(String.format(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, exception.getMessage()), Objects.requireNonNull(responseEntity.getBody()).getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.toString(), responseEntity.getBody().getStatus());
        assertNotNull(responseEntity.getBody().getTimestamp());
        assertEquals(LocalDateTime.now().getDayOfYear(), responseEntity.getBody().getTimestamp().getDayOfYear());
    }

    @Test
    void itShouldHandleMaxSizeExceededExceptionSuccessfully() {
        MaxSizeExceededException exception = TestData.getMaxSizeExceededException();

        ResponseEntity<ExceptionResponse> responseEntity = controllerAdvisor.handleMaxSizeExceededException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(exception.getMessage(), Objects.requireNonNull(responseEntity.getBody()).getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.toString(), responseEntity.getBody().getStatus());
        assertNotNull(responseEntity.getBody().getTimestamp());
        assertEquals(LocalDateTime.now().getDayOfYear(), responseEntity.getBody().getTimestamp().getDayOfYear());
    }

    @Test
    void itShouldHandleTechnologyAlreadyExistsExceptionSuccessfully() {
        TechnologyAlreadyExistsException exception = TestData.getTechnologyAlreadyExistsException();

        ResponseEntity<ExceptionResponse> responseEntity = controllerAdvisor.technologyAlreadyExistsException(exception);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals(exception.getMessage(), Objects.requireNonNull(responseEntity.getBody()).getMessage());
        assertEquals(HttpStatus.CONFLICT.toString(), responseEntity.getBody().getStatus());
        assertNotNull(responseEntity.getBody().getTimestamp());
        assertEquals(LocalDateTime.now().getDayOfYear(), responseEntity.getBody().getTimestamp().getDayOfYear());
    }
}