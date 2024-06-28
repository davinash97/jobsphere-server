package com.portal.jobsphere.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	CaffeineCacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager();
		cacheManager.setCaffeine(caffeineCacheBuilder());
		return cacheManager;
	}

	@Bean
	Caffeine<Object, Object> caffeineCacheBuilder() {
		return Caffeine.newBuilder()
				.initialCapacity(100)
				.maximumSize(500)
				.expireAfterWrite(10, java.util.concurrent.TimeUnit.MINUTES)
				.recordStats();
	}
}
