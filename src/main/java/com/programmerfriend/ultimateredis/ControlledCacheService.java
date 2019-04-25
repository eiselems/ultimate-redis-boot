package com.programmerfriend.ultimateredis;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ControlledCacheService {

    private static final String CONTROLLED_PREFIX = "myControlledPrefix_";

    public static String getCacheKey(String relevant){
        return CONTROLLED_PREFIX + relevant;
    }

    @Cacheable(cacheNames = "myControlledCache", key = "T(com.programmerfriend.ultimateredis.ControlledCacheService).getCacheKey(#relevant)")
    public String getFromCache(String relevant) {
        return null;
    }

    @CachePut(cacheNames = "myControlledCache", key = "T(com.programmerfriend.ultimateredis.ControlledCacheService).getCacheKey(#relevant)")
    public String populateCache(String relevant, String unrelevantTrackingId) {
        return "this is it again!";
    }

    @CacheEvict(cacheNames = "myControlledCache", key = "T(com.programmerfriend.ultimateredis.ControlledCacheService).getCacheKey(#relevant)")
    public void removeFromCache(String relevant) {
    }

}
