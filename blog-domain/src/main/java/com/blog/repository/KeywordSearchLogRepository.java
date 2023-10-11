package com.blog.repository;

import com.blog.model.entity.keywordSearchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface KeywordSearchLogRepository extends JpaRepository<keywordSearchLog, Long>, KeywordSearchLogRepositoryCustom {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE) // 베타락처리
    keywordSearchLog findFirstByKeywordEquals(String keyword);
}