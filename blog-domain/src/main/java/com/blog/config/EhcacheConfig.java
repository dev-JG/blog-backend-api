package com.blog.config;

import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class EhcacheConfig {

    @Bean
    public EhCacheCacheManager cacheManager() {
        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        net.sf.ehcache.CacheManager manager = net.sf.ehcache.CacheManager.create(config);

        CacheConfiguration getCacheConfig = new CacheConfiguration()
                .maxEntriesLocalHeap(1000)
                .maxEntriesLocalDisk(10000)
                .eternal(false)
                .timeToIdleSeconds(120)
                .timeToLiveSeconds(120)
                .memoryStoreEvictionPolicy("LRU")
                .transactionalMode(CacheConfiguration.TransactionalMode.OFF)
                .persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP))
                .name("scrapCacheManager");

        Cache getAuthenticatedMenuByUriCache = new Cache(getCacheConfig);
        manager.addCache(getAuthenticatedMenuByUriCache);

        return new EhCacheCacheManager(manager);
    }
}