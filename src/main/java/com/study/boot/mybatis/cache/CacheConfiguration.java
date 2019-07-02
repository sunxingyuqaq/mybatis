package com.study.boot.mybatis.cache;

import com.study.boot.mybatis.constants.BaseConstants;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xingyu Sun
 * @date 2019/1/10 13:29
 */
@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        List<String> cacheNames = new ArrayList<>();
        cacheNames.add(BaseConstants.APP_CACHE);
        cacheNames.add(BaseConstants.USER_CACHE);
        cacheManager.setCacheNames(cacheNames);
        return cacheManager;
    }
}
