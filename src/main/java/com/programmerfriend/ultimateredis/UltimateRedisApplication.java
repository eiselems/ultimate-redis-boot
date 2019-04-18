package com.programmerfriend.ultimateredis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;

@EnableCaching
@SpringBootApplication
@Slf4j
public class UltimateRedisApplication implements CommandLineRunner {

    @Autowired
    CacheService cacheService;

    public static void main(String[] args) {
        SpringApplication.run(UltimateRedisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String firstString = cacheService.cacheThis();
        log.info("First: {}", firstString);
        String secondString = cacheService.cacheThis();
        log.info("Second: {}", secondString);

    }
}
