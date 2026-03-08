package com.shawty.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
/*
    activates spring's cache abstraction layer
    Spring internally uses a Proxy Design Pattern to intercept calls and apply caching
 */
@EnableCaching
public class RedisConfig {
}
