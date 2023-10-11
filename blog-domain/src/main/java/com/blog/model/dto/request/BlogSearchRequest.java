package com.blog.model.dto.request;

import com.blog.model.enums.SortType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@Setter
public class BlogSearchRequest {
    private String query;
    private SortType sort = SortType.ACCURACY;
    private int page = 1;
    private int size = 10;

    public MultiValueMap<String, String> toMultiValueMap() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", getQuery());
        params.add("sort", getSort().getSort());
        params.add("page", String.valueOf(getPage()));
        params.add("size", String.valueOf(getSize()));
        return params;
    }
}