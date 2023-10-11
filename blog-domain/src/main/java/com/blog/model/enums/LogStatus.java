package com.blog.model.enums;

import lombok.Getter;

@Getter
public enum LogStatus {

    DISPLAY("DISPLAY", "노출"),
    NOT_DISPLAY("NOT_DISPLAY", "비노출");

    private String status;
    private String description;

    LogStatus(String status, String description) {
        this.status = status;
        this.description = description;
    }
}
