package com.blog.repository;

import com.blog.model.entity.keywordSearchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface KeywordSearchLogRepository extends JpaRepository<keywordSearchLog, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    keywordSearchLog findFirstByKeywordEquals(String keyword);
}