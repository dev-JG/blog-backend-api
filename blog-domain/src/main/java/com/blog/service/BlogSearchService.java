package com.blog.service;

import com.blog.client.KakaoBlogApiClient;
import com.blog.client.NaverBlogApiClient;
import com.blog.model.dto.request.BlogSearchRequest;
import com.blog.model.dto.response.BlogSearchResponse;
import com.blog.model.dto.response.kakao.DocumentResponse;
import com.blog.model.dto.response.kakao.MetaResponse;
import com.blog.model.dto.response.naver.ItemResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BlogSearchService {

    private final KakaoBlogApiClient kakaoBlogApiClient;
    private final NaverBlogApiClient naverBlogApiClient;
    private final AsyncBlogSearchService asyncBlogSearchService;

    @CircuitBreaker(name = "searchBlog", fallbackMethod = "searchBlogWithConditionAndNaver")
    public BlogSearchResponse<MetaResponse, DocumentResponse> searchBlogWithConditionAndKakao(
            BlogSearchRequest searchRequest
    ) {
        asyncBlogSearchService.asyncSearchCountUp(searchRequest.getQuery());
        return kakaoBlogApiClient.callSearchBlog(searchRequest);
    }

    public BlogSearchResponse<MetaResponse, DocumentResponse> searchBlogWithConditionAndNaver(
            BlogSearchRequest searchRequest,
            Throwable throwable
    ) {
        log.debug("##### BlogSearchService searchBlogWithConditionAndKakao CircuitBreaker error : {}",
                throwable.getMessage());

        var responses = naverBlogApiClient.callSearchBlog(searchRequest);
        var dto = new BlogSearchResponse<MetaResponse, DocumentResponse>();
        dto.setMeta(responses.convertMeta());
        dto.setDocuments(
                responses.getItems()
                        .stream()
                        .map(ItemResponse::convertDocument)
                        .collect(Collectors.toList())
        );

        return dto;
    }
}