package com.example.L14redisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    private String prefix = "blog_";

//    private Long nextId = 1l; // not generating and incrementing the blog id in java code (see below explanation)

    private String nextIdKey = "nextId";

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public Long createBlog(Blog blog){

        // generating and storing the blog id in the redis server,
        // not in this java code as when server restarts the blog id again reset and
        // next blog overrides the already existing blog as id matches.
        // This can also happen when the multiple instance of the application is running as each generate same key.
        Long id = redisTemplate.opsForValue().increment(nextIdKey); // increment also create the key on the redis server
                                                                    // if it doesn't exist and increment it to 1,
                                                                    // increment is atomic, so
                                                                    // it will also work well with multiple instance of
                                                                    // the application server.
                                                                    // Also, nextIdKey is a String, so it already
                                                                    // implements Serializable
        String key = prefix+id;
        redisTemplate.opsForValue().set(key,blog); // redisTemplate will serialize the key and value
                                                    // before storing to the redis server,
                                                    // as key is String, so it already implements Serializable,
                                                    // while we made Blog class to implements Serializable
        return id;
    }


    public Blog getBlog(Long id){
        String key = prefix+id;
        Blog blog = (Blog) redisTemplate.opsForValue().get(key); // get returns Object type
        return blog;
    }
}
