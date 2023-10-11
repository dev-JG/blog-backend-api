package com.blog.repository.impl;

import static com.blog.model.entity.QkeywordSearchLog.keywordSearchLog;

import com.blog.model.dto.response.PopularKeywordResponse;
import com.blog.model.dto.response.QPopularKeywordResponse;
import com.blog.model.enums.LogStatus;
import com.blog.repository.KeywordSearchLogRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class KeywordSearchLogRepositoryCustomImpl implements KeywordSearchLogRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PopularKeywordResponse> findPopularKeywordTopTen() {
        return jpaQueryFactory
                .select(
                        new QPopularKeywordResponse(
                                keywordSearchLog.keyword,
                                keywordSearchLog.count,
                                keywordSearchLog.lastSearchDate
                        )
                )
                .from(keywordSearchLog)
                .where(keywordSearchLog.status.eq(LogStatus.DISPLAY))
                .orderBy(keywordSearchLog.count.desc())
                .limit(10)
                .fetch();
    }
}