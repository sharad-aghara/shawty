package com.shawty.ratelimit;

import io.github.bucket4j.Bucket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimitConfig {

    @Bean
    public Bucket bucket() {

        return Bucket.builder()
                .addLimit(limit -> limit.capacity(5).refillGreedy(5, Duration.ofMinutes(1)))
                .build();
    }
}
