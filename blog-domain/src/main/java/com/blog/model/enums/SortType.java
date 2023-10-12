package com.blog.model.enums;

import lombok.Getter;

@Getter
public enum SortType {

    ACCURACY("accuracy", "sim", "정확도순"),
    RECENCY("recency", "date", "최신순");

    private String kakaoSort;
    private String naverSort;
    private String description;

    SortType(String kakaoSort, String naverSort, String description) {
        this.kakaoSort = kakaoSort;
        this.naverSort = naverSort;
        this.description = description;
    }
}