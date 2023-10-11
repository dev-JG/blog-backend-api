package com.blog.service;

import com.blog.client.KakaoBlogApiClient;
import com.blog.model.dto.request.BlogSearchRequest;
import com.blog.model.dto.response.BlogSearchResponse;
import com.blog.model.dto.response.DocumentResponse;
import com.blog.model.dto.response.MetaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogSearchService {

    private final KakaoBlogApiClient kakaoBlogApiClient;

    public BlogSearchResponse<MetaResponse, DocumentResponse> searchBlogWithCondition(
            BlogSearchRequest searchRequest
    ) {

        // todo 검색한 키워드 카운트를 비동기 디비저장 / 횟수

        // todo 카카오 실패시 네이버 호출하도록

        return kakaoBlogApiClient.callSearchBlog(searchRequest);
    }
}