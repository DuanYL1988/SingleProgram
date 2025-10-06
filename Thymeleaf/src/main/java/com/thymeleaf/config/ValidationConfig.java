package com.thymeleaf.config;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import com.thymeleaf.model.ResponseDto;

// @ControllerAdvice
public class ValidationConfig {

    // @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseDto> handle(BindException e) {
        List<FieldError> errors = e.getFieldErrors();
        return new ResponseEntity<>(new ResponseDto("400", "input error", errors), HttpStatus.BAD_REQUEST);
    }

}
