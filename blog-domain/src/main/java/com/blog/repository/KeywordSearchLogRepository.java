package com.blog.repository;

import com.blog.model.entity.keywordSearchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface KeywordSearchLogRepository extends JpaRepository<keywordSearchLog, Long>, KeywordSearchLogRepositoryCustom {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE) // 배타락처리
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "1000")})
    keywordSearchLog findFirstByKeywordEquals(String keyword);
}