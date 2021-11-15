package com.yxtl.business.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author px
 * @date 2021/10/8 11:43
 */
@Service
public class RedisService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public <T> void set(String key, T value) {
        redisTemplate.boundValueOps(key).set(value);
    }

    public <T> void set(String key, T value, long timeout) {
        redisTemplate.boundValueOps(key).set(value, timeout, TimeUnit.SECONDS);
    }

    public <T> void setList(String key, List<T> value, long timeout) {
        redisTemplate.boundListOps(key).rightPushAll(value);
        setExpire(key, timeout);
    }

    public <T> void setList(String key, List<T> value) {
        redisTemplate.boundListOps(key).rightPushAll(value);
    }

    public <T> List<T> getList(String key) {
        return (List<T>) redisTemplate.boundListOps(key).range(0, redisTemplate.boundListOps(key).size());
    }

    public <T> T get(String key) {
        return (T) redisTemplate.boundValueOps(key).get();
    }

    public <T> T multiGet(List<String> keys) {
        return (T) redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 可以批量插入，但无法批量设置key的过期时间，也就只能默认ttl=-1
     *
     * @param map
     * @param <T>
     */
    public <T> void multiSet(Map<String, T> map) {
        redisTemplate.opsForValue().multiSetIfAbsent(map);
    }

    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public <T> boolean setExpire(String key, Long timeout) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, TimeUnit.SECONDS));
    }

    public <T> T get(String key, Class<T> value) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return (T) operations.get(key);
    }

    public <T> boolean delete(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    public <T> boolean addToSet(String key, T value) {
        BoundSetOperations<String, T> operations = (BoundSetOperations<String, T>) redisTemplate.boundSetOps(key);
        operations.add(value);
        return true;
    }

    public <T> boolean deleteFromSet(String key, T value) {
        BoundSetOperations<String, Object> operations = redisTemplate.boundSetOps(key);
        operations.remove(value);
        return true;
    }

    public <T> List<T> getFromSet(String key) {
        BoundSetOperations<String, T> operations = (BoundSetOperations<String, T>) redisTemplate.boundSetOps(key);
        Set<T> mems = operations.members();
        return new ArrayList<T>(mems);
    }

}
