package com.yyb.springmvc.config;


import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class CachingConfig {    public CacheManager cacheManager(net.sf.ehcache.CacheManager cm, javax.cache.CacheManager jcm) {
    CompositeCacheManager cacheManager = new CompositeCacheManager();
    List<CacheManager> managers = new ArrayList<>();
    managers.add(new JCacheCacheManager(jcm));
    managers.add(new EhCacheCacheManager(cm));
    managers.add(new RedisCacheManager(redisTemplate(redisConnectionFactory())));
    cacheManager.setCacheManagers(managers);
    return cacheManager;
}


    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        //Redis缓存管理器Bean
        return new RedisCacheManager(redisTemplate);
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String,String>redisTemplate(RedisConnectionFactory redisCF) {
        RedisTemplate<String,String> redisTemplate =new RedisTemplate<String,String>();
        redisTemplate.setConnectionFactory(redisCF);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
