package com.blog.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.blog.repository.KeywordSearchLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KeywordSearchServiceTest {

    private final KeywordSearchService keywordSearchService;

    public KeywordSearchServiceTest(
            @Autowired KeywordSearchLogRepository keywordSearchLogRepository
    ) {
        this.keywordSearchService = new KeywordSearchService(keywordSearchLogRepository);
    }

    @Test
    void 인기검색어_조회_테스트() {
        // given
        var keywordSearchService = this.keywordSearchService;

        // when
        var popularKeywordList = keywordSearchService.getPopularKeywordList();

        // then
        assertThat(popularKeywordList).hasSizeGreaterThan(1);
        assertThat(popularKeywordList).isNotEmpty();
    }
}