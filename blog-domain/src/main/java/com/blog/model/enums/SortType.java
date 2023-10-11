package com.blog.model.enums;

import lombok.Getter;

@Getter
public enum SortType {

    ACCURACY("accuracy", "정확도순"),
    RECENCY("recency", "최신순");

    private String sort;
    private String description;

    SortType(String sort, String description) {
        this.sort = sort;
        this.description = description;
    }
}