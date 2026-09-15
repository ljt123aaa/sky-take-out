package com.sky.test;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
public class SpringDataRedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisTemplate() {
        System.out.println(redisTemplate);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        HashOperations hashOperations = redisTemplate.opsForHash();
        ListOperations listOperations = redisTemplate.opsForList();
        SetOperations setOperations = redisTemplate.opsForSet();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        // System.out.println(zSetOperations);
        // System.out.println(valueOperations.get("key"));
        // System.out.println(hashOperations.get("key", "field"));
        // System.out.println(listOperations.range("key", 0, -1));
        // System.out.println(setOperations.members("key"));
        // System.out.println(zSetOperations.range("key", 0, -1));
    }

    // 操作字符串类型的数据
    @Test
    public void testString() {
        // set get setex setnx
        redisTemplate.opsForValue().set("didida", "吕神布");
        String name = (String) redisTemplate.opsForValue().get("didida");
        System.out.println(name);
        redisTemplate.opsForValue().set("werwer", "18", 1, TimeUnit.MINUTES);
        redisTemplate.opsForValue().setIfAbsent("asd", "182");
        redisTemplate.opsForValue().setIfAbsent("asd", "183");
    }

    @Test
    public void testHash() {
        // hset hget hdel hkeys hvals
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("999", "name", "tom");
        hashOperations.put("999", "age", "18");

        String name = (String) hashOperations.get("999", "name");
        System.out.println(name);
        String age = (String) hashOperations.get("999", "age");
        System.out.println(age);

        Set keys = hashOperations.keys("999");
        System.out.println(keys);

        List vals = hashOperations.values("999");
        System.out.println(vals);

        hashOperations.delete("999", "age");
    }

    // 操作列表类型的数据
    @Test
    public void testList() {
        // lpush lpop lrange ltrim lset
        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.leftPushAll("list", "a", "b", "c");

        List vals = listOperations.range("list", 0, -1);
        System.out.println(vals);

        listOperations.leftPush("list", "1");
        listOperations.leftPush("list", "2");
        listOperations.leftPush("list", "3");

        listOperations.rightPop("list");

        Long size = listOperations.size("list");
        System.out.println(size);
        System.out.println(listOperations.range("list", 0, -1));
    }

    // 操作集合类型的数据
    @Test
    public void testSet() {
        // sadd smembers srem scard sinter sunion sdiff
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add("set1", "a", "b", "c", "d");
        setOperations.add("set2", "a", "b", "x", "y");

        Set members = setOperations.members("set1");
        System.out.println(members);

        Long size = setOperations.size("set1");
        System.out.println(size);

        Set intersect = setOperations.intersect("set1", "set2");
        System.out.println(intersect);

        Set union = setOperations.union("set1", "set2");
        System.out.println(union);

        setOperations.remove("set1", "a");
    }

    // 操作有序集合类型的数据
    @Test
    public void testZSet() {
        // zadd zrange zrem zincrby zrem
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("zset1", "a", 100);
        zSetOperations.add("zset1", "b", 200);
        zSetOperations.add("zset1", "c", 300);

        Set vals = zSetOperations.range("zset1", 0, -1);
        System.out.println(vals);

        zSetOperations.incrementScore("zset1", "a", 10);

        zSetOperations.remove("zset1", "a", "b");
    }

    // 通用命令操作
    @Test
    public void testCommon() {
        // keys exists type del
        Set keys = redisTemplate.keys("*");
        System.out.println(keys);

        Boolean exists = redisTemplate.hasKey("didida");
        System.out.println(exists);
        Boolean werwer = redisTemplate.hasKey("werwer");
        System.out.println(werwer);

        for (Object key : keys) {
            System.out.println(key);
        }

        redisTemplate.delete("zset1");
    }
}
