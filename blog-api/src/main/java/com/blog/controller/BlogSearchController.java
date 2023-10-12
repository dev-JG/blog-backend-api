package com.blog.controller;

import com.blog.model.dto.request.BlogSearchRequest;
import com.blog.model.dto.response.BlogSearchResponse;
import com.blog.model.dto.response.kakao.DocumentResponse;
import com.blog.model.dto.response.kakao.MetaResponse;
import com.blog.service.BlogSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vi/blog/search")
public class BlogSearchController {

    private final BlogSearchService blogSearchService;

    @GetMapping("")
    public ResponseEntity<BlogSearchResponse<MetaResponse, DocumentResponse>> createScrapImageInfo(
            @ModelAttribute BlogSearchRequest searchRequest
    ) {
        return ResponseEntity.ok(
                blogSearchService.searchBlogWithConditionAndKakao(searchRequest)
        );
    }
}