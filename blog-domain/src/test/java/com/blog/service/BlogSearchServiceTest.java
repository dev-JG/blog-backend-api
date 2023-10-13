package com.blog.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.blog.client.KakaoBlogApiClient;
import com.blog.client.NaverBlogApiClient;
import com.blog.model.dto.request.BlogSearchRequest;
import com.blog.model.enums.SortType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BlogSearchServiceTest {

    private final BlogSearchService blogSearchService;

    public BlogSearchServiceTest(
            @Autowired KakaoBlogApiClient kakaoBlogApiClient,
            @Autowired NaverBlogApiClient naverBlogApiClient,
            @Autowired AsyncBlogSearchService asyncBlogSearchService
    ) {
        this.blogSearchService = new BlogSearchService(kakaoBlogApiClient, naverBlogApiClient, asyncBlogSearchService);
    }

    @Test
    void 블로그_검색_테스트_카카오() {
        // given
        var blogSearchService = this.blogSearchService;
        var searchRequest = new BlogSearchRequest();
        searchRequest.setQuery("과자");
        searchRequest.setSort(SortType.ACCURACY);
        searchRequest.setPage(1);
        searchRequest.setSize(10);

        // when
        var response = blogSearchService.searchBlogWithConditionAndKakao(searchRequest);

        // then
        assertThat(response.getDocuments()).hasSizeGreaterThan(1);
        assertThat(response).isNotNull();
    }

    @Test
    void 블로그_검색_테스트_네이버() {
        // given
        var blogSearchService = this.blogSearchService;
        var searchRequest = new BlogSearchRequest();
        searchRequest.setQuery("과자");
        searchRequest.setSort(SortType.ACCURACY);
        searchRequest.setPage(1);
        searchRequest.setSize(10);
        var throwable = new Throwable("강제 에러 테스트입니다.");

        // when
        var response = blogSearchService.searchBlogWithConditionAndNaver(searchRequest, throwable);

        // then
        assertThat(response.getDocuments()).hasSizeGreaterThan(1);
        assertThat(response).isNotNull();
    }
}