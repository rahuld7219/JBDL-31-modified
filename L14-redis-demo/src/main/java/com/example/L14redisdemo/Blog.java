package com.example.L14redisdemo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Blog implements Serializable { // as RedisTemplate serialize/deserialize the Blog while storing/retrieving the Blog
                                            // from the Redis server, so it must implements Serializable(I)
    private String title;
    private String author;
    private String content;
    private Date publishDate;
}
