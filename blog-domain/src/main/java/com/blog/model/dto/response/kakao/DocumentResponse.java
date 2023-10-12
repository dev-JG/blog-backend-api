package com.blog.model.dto.response.kakao;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentResponse {
    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private String datetime;
}