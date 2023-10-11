package com.blog.client;

import com.blog.model.dto.request.BlogSearchRequest;
import com.blog.model.dto.response.BlogSearchResponse;
import com.blog.model.dto.response.DocumentResponse;
import com.blog.model.dto.response.MetaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Component
public class KakaoBlogApiClient {

    private final static String API_KEY = " KakaoAK 119d0fc8c40f372fbe8ceea600d3cc49";
    private final RestTemplate restTemplate;

    public BlogSearchResponse<MetaResponse, DocumentResponse> callSearchBlog(BlogSearchRequest searchRequest) {

        var headers = new HttpHeaders();
        headers.set("Authorization", API_KEY);

        var uri = UriComponentsBuilder
                .fromUriString("https://dapi.kakao.com")
                .path("/v2/search/blog")
                .queryParams(searchRequest.toMultiValueMap())
                .encode()
                .build()
                .toUri();

        return restTemplate.exchange(
                uri,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<BlogSearchResponse<MetaResponse, DocumentResponse>>() {}
        ).getBody();
    }
}