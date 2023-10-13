package com.blog.service;

import com.blog.repository.KeywordSearchLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AsyncBlogSearchServiceTest {

    private final PlatformTransactionManager transactionManager;
    private final AsyncBlogSearchService asyncBlogSearchService;
    private final KeywordSearchLogRepository keywordSearchLogRepository;

    public AsyncBlogSearchServiceTest(
            @Autowired KeywordSearchLogRepository keywordSearchLogRepository,
            @Autowired PlatformTransactionManager transactionManager
    ) {
        this.asyncBlogSearchService = new AsyncBlogSearchService(keywordSearchLogRepository);
        this.keywordSearchLogRepository = keywordSearchLogRepository;
        this.transactionManager = transactionManager;
    }

    @Test
    void 검색카운트_저장_동시성_테스트() {
        // given
        var asyncBlogSearchService = this.asyncBlogSearchService;
        var keywordSearchLogRepository = this.keywordSearchLogRepository;
        var keyword = "과자";
        int totalRequests = 30;
        var futures = new CompletableFuture[totalRequests];

        // when
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            for (int i = 0; i < totalRequests; i++) {
                futures[i] = CompletableFuture.runAsync(() -> asyncBlogSearchService.asyncSearchCountUp(keyword));
            }
            CompletableFuture.allOf(futures).get();

            // then
            assertThat(keywordSearchLogRepository.findFirstByKeywordEquals(keyword).getCount()).isEqualTo(31);

            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
    }
}