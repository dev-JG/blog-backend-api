package com.blog.model.dto.response.naver;

import com.blog.model.dto.response.kakao.MetaResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class NaverSearchResponse {
    private int total;
    private int start;
    private int display;
    private String lastBuildDate;
    private List<ItemResponse> items;

    public MetaResponse convertMeta() {
        var dto = new MetaResponse();
        dto.setTotalCount(total);
        dto.setPageableCount(display);
        dto.setEnd(total < (start * display)); // 마지막 페이지 계산
        return dto;
    }
}