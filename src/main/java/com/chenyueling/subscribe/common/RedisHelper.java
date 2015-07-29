package com.chenyueling.subscribe.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by chenyueling on 2015/5/1.
 */
public class RedisHelper {


    private static final JedisPool jedisPool = new JedisPool();


    public static final Jedis getRedis(){
        return jedisPool.getResource();
    }


    public static final void returnResource(Jedis jedis){
        jedisPool.returnResourceObject(jedis);
    }



    public static void main(String[] args) {
        JedisPool jedisPool =new JedisPool();
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.get("h"));

    }
}
