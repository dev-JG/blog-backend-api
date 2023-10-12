package com.blog.model.dto.response.naver;

import com.blog.model.dto.response.kakao.DocumentResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemResponse {
    private String title;
    private String link;
    private String description;
    private String bloggername;
    private String bloggerlink;
    private String postdate;

    public DocumentResponse convertDocument() {
        var dto = new DocumentResponse();
        dto.setTitle(title);
        dto.setContents(description);
        dto.setUrl(link);
        dto.setBlogname(bloggername);
        dto.setDatetime(postdate);
        return dto;
    }
}