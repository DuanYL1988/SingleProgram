package com.thymeleaf.model;

import java.util.List;

import org.springframework.validation.FieldError;

public class ResponseDto {

    public ResponseDto(String cd, String msg, List<FieldError> msgList) {
        this.code = cd;
        this.message = msg;
        this.messages = msgList;
    }

    private String code;

    private String message;

    private List<FieldError> messages;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldError> getMessages() {
        return messages;
    }

    public void setMessages(List<FieldError> messages) {
        this.messages = messages;
    }

}
