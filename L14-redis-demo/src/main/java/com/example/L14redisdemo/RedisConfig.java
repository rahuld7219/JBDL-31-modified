package com.example.L14redisdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
//
//    @Value("${redis.url}")
//    private String redisUrl;
//
//    @Value("${redis.password}")
//    private String password;
//
//    @Value("${redis.port}")
//    private Integer port;
//
//
//    @Bean // To make connection to the Redis server, We can do the connection with spring.redis properties in the properties file, instead of creating this bean
//    public RedisConnectionFactory getRedisConnectionFactory(){ // RedisConnectionFactory have 2 implementation here
//                                                              // one is LettuceConnectionFactory and the other is
//                                                              // JedisConnectionFactory, as we have added Lettuce,
//                                                              // so we use Lettuce one
////        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
////        lettuceConnectionFactory.setHostName(redisUrl);
////        lettuceConnectionFactory.setPort(port);
////        return lettuceConnectionFactory;

//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setHostName(redisUrl);
//        jedisConnectionFactory.setPort(port);
//        return jedisConnectionFactory;

//    }

    @Bean // To communicate to the redis server via the connection made above we need RedisTemplate
    public RedisTemplate<String,Object> getRedisTemplate(RedisConnectionFactory redisConnectionFactory){

        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(redisConnectionFactory);

        redisTemplate.setKeySerializer(new StringRedisSerializer()); // we use StringRedisSerializer() for key,
                                                                        // so keys on redis shown as string
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer()); // we use JdkSerializationRedisSerializer for value,
                                                                                    // so values on redis will be shown in
                                                                                    // some hexa unreadable form,
                                                                                    // we can use StringRedisSerializer also
                                                                                    // if we want values to be shown as string on redis,
                                                                                    // there are other options also for serialization
        return redisTemplate;
    }

}
