package ru.university.config;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.university.model.CachedCurrencyCustomer;

@Configuration
public class ApplicationConfig {
    private final CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);

    @Bean
    public Cache<Integer, CachedCurrencyCustomer> currencyCustomerCache(@Value("${app.cache.size}") int cacheSize) {
        return cacheManager.createCache("CurrencyCustomer-Cache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, CachedCurrencyCustomer.class,
                        ResourcePoolsBuilder.heap(cacheSize))
                        .build());
    }
}
