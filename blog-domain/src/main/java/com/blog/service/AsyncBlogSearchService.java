package com.blog.service;

import com.blog.model.entity.keywordSearchLog;
import com.blog.model.enums.LogStatus;
import com.blog.repository.KeywordSearchLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AsyncBlogSearchService {

    private final KeywordSearchLogRepository keywordSearchLogRepository;

    @Async("searchCountThreadPoolTaskExecutor")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void asyncSearchCountUp(String keyword) {

        var logEntity = keywordSearchLogRepository.findFirstByKeywordEquals(keyword);

        if (Objects.isNull(logEntity)) { // 기존에 조회된 적이 없는 케이스
            logEntity = new keywordSearchLog();
            logEntity.setKeyword(keyword);
            logEntity.setCount(1);
            logEntity.setStatus(LogStatus.DISPLAY);
        } else {
            logEntity.setCount(logEntity.getCount()+1);
        }
        logEntity.setLastSearchDate(LocalDateTime.now());

        keywordSearchLogRepository.save(logEntity);
    }
}