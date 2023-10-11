package com.blog.model.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MetaResponse {
    @JsonAlias("total_count")
    private int totalCount;
    @JsonAlias("pageable_count")
    private int pageableCount;
    @JsonAlias("is_end")
    private boolean isEnd;
}