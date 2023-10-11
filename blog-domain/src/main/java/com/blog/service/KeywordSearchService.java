package com.blog.service;

import com.blog.model.dto.response.PopularKeywordResponse;
import com.blog.repository.KeywordSearchLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KeywordSearchService {

    private final KeywordSearchLogRepository keywordSearchLogRepository;

    @Cacheable(value = "twoMinuteCacheManager", key = "'popularKeyword'")
    public List<PopularKeywordResponse> getPopularKeywordList() {
        return keywordSearchLogRepository.findPopularKeywordTopTen();
    }
}