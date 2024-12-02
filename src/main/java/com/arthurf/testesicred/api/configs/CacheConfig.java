package com.arthurf.testesicred.api.configs;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * Cache name for CPF validation
     */
    public static final String CACHE_CPF_VALIDATOR = "CPF_VALIDATOR";

    @Bean
    public Cache cacheOne() {
        return new GuavaCache(CACHE_CPF_VALIDATOR, CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .build());
    }
}
