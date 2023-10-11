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
    private final AsyncBlogSearchService asyncBlogSearchService;

    public BlogSearchResponse<MetaResponse, DocumentResponse> searchBlogWithCondition(
            BlogSearchRequest searchRequest
    ) {
        asyncBlogSearchService.asyncSearchCountUp(searchRequest.getQuery());

        // todo 카카오 실패시 네이버 호출하도록

        return kakaoBlogApiClient.callSearchBlog(searchRequest);
    }
}