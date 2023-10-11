package com.blog.model.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
public class PopularKeywordResponse implements Serializable {
    private String keyword;
    private int count;
    private LocalDateTime lastSearchDate;

    @QueryProjection
    public PopularKeywordResponse(String keyword, int count, LocalDateTime lastSearchDate) {
        this.keyword = keyword;
        this.count = count;
        this.lastSearchDate = lastSearchDate;
    }
}