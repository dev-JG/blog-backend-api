package com.blog.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BlogSearchResponse<E, T> {
    private E meta;
    private List<T> documents;
}