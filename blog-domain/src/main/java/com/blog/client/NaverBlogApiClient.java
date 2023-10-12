package com.blog.client;

import com.blog.model.dto.request.BlogSearchRequest;
import com.blog.model.dto.response.naver.NaverSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Component
public class NaverBlogApiClient {

    private final static String API_URL = "https://openapi.naver.com";
    private final static String CLIENT_ID = "QGn8cbx3o2GmdOq9VvVr";
    private final static String CLIENT_SECRET = "ek7wytlXhM";
    private final static String BLOG_SEARCH_PATH_URL = "/v1/search/blog";
    private final RestTemplate restTemplate;

    public NaverSearchResponse callSearchBlog(BlogSearchRequest searchRequest) {

        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret", CLIENT_SECRET);

        var uri = UriComponentsBuilder
                .fromUriString(API_URL)
                .path(BLOG_SEARCH_PATH_URL)
                .queryParams(searchRequest.toMultiValueMapWithNaver())
                .encode()
                .build()
                .toUri();

        return restTemplate.exchange(
                uri,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<NaverSearchResponse>() {}
        ).getBody();
    }
}