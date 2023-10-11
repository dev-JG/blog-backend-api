package com.blog.model.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomExceptionStatus {

    INVALID_KEYWORD("유요하지않은 검색어입니다.", HttpStatus.NOT_FOUND);

    private String message;
    private final HttpStatus httpStatus;

    CustomExceptionStatus(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
