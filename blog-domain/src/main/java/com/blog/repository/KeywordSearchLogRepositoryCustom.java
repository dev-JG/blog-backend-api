package com.blog.repository;

import com.blog.model.dto.response.PopularKeywordResponse;

import java.util.List;

public interface KeywordSearchLogRepositoryCustom {

    List<PopularKeywordResponse> findPopularKeywordTopTen();
}