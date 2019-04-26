# ultimate-redis-boot
This is an example repository to discover ways of interacting with Redis (from a Spring Boot 2 Application).

The blog post about this repository can be found [HERE](https://programmerfriend.com/ultimate-guide-to-redis-cache-with-spring-boot-2-and-spring-data-redis/?gthb).

![Spring Boot Redis](https://programmerfriend.com/wp-content/uploads/2019/01/redis-boot.png "Spring Boot Redis")

## Run the service
```
./mvnw spring-boot:run
```

## What it is / What it does
* A Spring Boot 2 Application
* Using @Cacheable, @CachePut, @CacheEvict to cache results of method invocations
* Use Redis to store the cached results
* Define different TTLs for different Caches