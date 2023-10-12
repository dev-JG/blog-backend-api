package com.blog.controller;

import com.blog.model.dto.response.PopularKeywordResponse;
import com.blog.service.KeywordSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/search/keyword")
public class KeywordController {

    private final KeywordSearchService keywordSearchService;

    @GetMapping("/popular")
    public ResponseEntity<List<PopularKeywordResponse>> getPopularKeywordList() {
        return ResponseEntity.ok(
                keywordSearchService.getPopularKeywordList()
        );
    }
}