package com.programmerfriend.ultimateredis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.UUID;

@EnableCaching
@SpringBootApplication
@Slf4j
public class UltimateRedisApplication implements CommandLineRunner {

    @Autowired
    CacheService cacheService;

    @Autowired
    ControlledCacheService controlledCacheService;

    public static void main(String[] args) {
        SpringApplication.run(UltimateRedisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String firstString = cacheService.cacheThis("param1", UUID.randomUUID().toString());
        log.info("First: {}", firstString);
        String secondString = cacheService.cacheThis("param1", UUID.randomUUID().toString());
        log.info("Second: {}", secondString);
        String thirdString = cacheService.cacheThis("AnotherParam", UUID.randomUUID().toString());
        log.info("Third: {}", thirdString);
        String fourthString = cacheService.cacheThis("AnotherParam", UUID.randomUUID().toString());
        log.info("Fourth: {}", fourthString);

        log.info("Starting controlled cache: -----------");
        String controlledFirst = getFromControlledCache("first");
        log.info("Controlled First: {}", controlledFirst);
        String controlledSecond = getFromControlledCache("second");
        log.info("Controlled Second: {}", controlledSecond);

        getFromControlledCache("first");
        getFromControlledCache("second");
        getFromControlledCache("third");
        //log.info("Clearing all cache entries:");
        //cacheService.forgetAboutThis("param1");
        //controlledCacheService.removeFromCache("controlledParam1");
    }

    private String getFromControlledCache(String param) {
        String fromCache = controlledCacheService.getFromCache(param);
        if (fromCache == null) {
            log.info("Oups - Cache was empty. Going to populate it");
            String newValue = controlledCacheService.populateCache(param, UUID.randomUUID().toString());
            log.info("Populated Cache with: {}", newValue);
            return newValue;
        }
        log.info("Returning from Cache: {}", fromCache);
        return fromCache;
    }
}
